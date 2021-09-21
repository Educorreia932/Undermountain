package world

import attributes.Vision
import extensions.GameEntity
import extensions.blocksVision
import extensions.position
import game.Game
import game.GameBlock
import game.GameContext
import org.hexworks.amethyst.api.Engine
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.amethyst.internal.TurnBasedEngine
import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.zircon.api.builder.game.GameAreaBuilder
import org.hexworks.zircon.api.data.Position
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size3D
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.game.GameArea
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.shape.EllipseFactory
import org.hexworks.zircon.api.shape.LineFactory
import org.hexworks.zircon.api.uievent.UIEvent
import kotlin.reflect.full.isSuperclassOf

class World(
    startingBlocks: Map<Position3D, GameBlock>,
    visibleSize: Size3D,
    actualSize: Size3D
) : GameArea<Tile, GameBlock> by GameAreaBuilder.newBuilder<Tile, GameBlock>()
    .withVisibleSize(visibleSize)
    .withActualSize(actualSize)
    .build() {

    private val engine: TurnBasedEngine<GameContext> = Engine.create()

    init {
        startingBlocks.forEach { (pos, block) ->
            setBlockAt(pos, block)
            block.entities.forEach { entity ->
                engine.addEntity(entity)
                entity.position = pos
            }
        }
    }

    /**
     * Adds the given [Entity] at the given [Position3D].
     * Has no effect if this world already contains the given [Entity].
     */
    fun addEntity(entity: Entity<EntityType, GameContext>, position: Position3D) {
        entity.position = position
        engine.addEntity(entity)

        fetchBlockAt(position).map {
            it.addEntity(entity)
        }
    }

    fun addAtEmptyPosition(
        entity: GameEntity<EntityType>,
        offset: Position3D = Position3D.create(0, 0, 0),
        size: Size3D = actualSize
    ): Boolean {
        return findEmptyLocationWithin(offset, size).fold(
            whenEmpty = {
                false
            },
            whenPresent = { location ->
                addEntity(entity, location)
                true
            })
    }

    /**
     * Finds an empty location within the given area (offset and size) on this [World].
     */
    private fun findEmptyLocationWithin(offset: Position3D, size: Size3D): Maybe<Position3D> { // 8
        var position = Maybe.empty<Position3D>()
        val maxTries = 10
        var currentTry = 0
        while (position.isPresent.not() && currentTry < maxTries) {
            val pos = Position3D.create(
                x = (Math.random() * size.xLength).toInt() + offset.x,
                y = (Math.random() * size.yLength).toInt() + offset.y,
                z = (Math.random() * size.zLength).toInt() + offset.z
            )

            fetchBlockAt(pos).map {
                if (it.isEmptyFloor) {
                    position = Maybe.of(pos)
                }
            }

            currentTry++
        }

        return position
    }

    fun update(screen: Screen, uiEvent: UIEvent, game: Game) {
        engine.executeTurn(
            GameContext(
                world = this,
                screen = screen,
                uiEvent = uiEvent,
                player = game.player
            )
        )
    }

    fun moveEntity(entity: GameEntity<EntityType>, position: Position3D): Boolean {
        var success = false
        val oldBlock = fetchBlockAt(entity.position)
        val newBlock = fetchBlockAt(position)

        if (bothBlocksPresent(oldBlock, newBlock)) {
            success = true
            oldBlock.get().removeEntity(entity)
            entity.position = position
            newBlock.get().addEntity(entity)
        }

        return success
    }

    private fun bothBlocksPresent(oldBlock: Maybe<GameBlock>, newBlock: Maybe<GameBlock>) =
        oldBlock.isPresent && newBlock.isPresent

    fun removeEntity(entity: Entity<EntityType, GameContext>) {
        fetchBlockAt(entity.position).map {
            it.removeEntity(entity)
        }

        engine.removeEntity(entity)
        entity.position = Position3D.unknown()
    }

    inline fun <reified T : EntityType> fetchEntitiesOfType(): MutableList<GameEntity<EntityType>> {
        val entities: MutableList<GameEntity<EntityType>> = mutableListOf()

        for (block: Map.Entry<Position3D, GameBlock> in blocks.entries) {
            if (block.value.isOccupied) {
                val entity = block.value.occupier.get()

                if (T::class.isSuperclassOf(entity.type::class))
                    entities.add(entity)
            }
        }

        return entities
    }

    private fun isVisionBlockedAt(position: Position3D): Boolean {
        return fetchBlockAt(position).fold(
            whenEmpty = { false },
            whenPresent = {
                it.entities.any(GameEntity<EntityType>::blocksVision)
            }
        )
    }

    fun findVisiblePositionsFor(entity: GameEntity<EntityType>): Iterable<Position> {
        val centerPosition = entity.position.to2DPosition()

        return entity.findAttribute(Vision::class).map { (radius) ->
            EllipseFactory.buildEllipse(
                fromPosition = centerPosition,
                toPosition = centerPosition.withRelativeX(radius).withRelativeY(radius)
            )
                .positions
                .flatMap { ringPosition ->
                    val result = mutableListOf<Position>()
                    val iter = LineFactory.buildLine(centerPosition, ringPosition).iterator()

                    do {
                        val next = iter.next()

                        result.add(next)
                    } while (iter.hasNext() && !isVisionBlockedAt(next.toPosition3D(entity.position.z)))
            
                    result
                }
        }.orElse(listOf())
    }
    
    fun addEntity(entity: GameEntity<EntityType>) {
        engine.addEntity(entity)
    }
}