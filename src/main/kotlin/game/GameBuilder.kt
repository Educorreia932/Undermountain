package game

import attributes.types.PlayerEntity
import builders.EntityFactory
import builders.WorldBuilder
import extensions.GameEntity
import game.GameConfig.LOG_AREA_HEIGHT
import game.GameConfig.SIDEBAR_WIDTH
import game.GameConfig.WINDOW_HEIGHT
import game.GameConfig.WINDOW_WIDTH
import game.GameConfig.WORLD_SIZE
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size3D

class GameBuilder(val worldSize: Size3D) {          // 1

    private val visibleSize = Size3D.create(        // 2
        xLength = WINDOW_WIDTH - SIDEBAR_WIDTH,
        yLength = WINDOW_HEIGHT - LOG_AREA_HEIGHT,
        zLength = 1
    )

    val world = WorldBuilder(worldSize)             // 3
        .makeCaves()
        .build(visibleSize = visibleSize)

    fun buildGame(): Game {

        prepareWorld()

        val player = addPlayer()

        return Game.create(
            player = player,
            world = world
        )
    }

    private fun prepareWorld() = also {             // 4
        world.scrollUpBy(world.actualSize.zLength)
    }

    private fun addPlayer(): GameEntity<PlayerEntity> {
        val player = EntityFactory.newPlayer()      // 5
        world.addAtEmptyPosition(
            player,                                 // 6
            offset = Position3D.create(0, 0, GameConfig.DUNGEON_LEVELS - 1), // 7
            size = world.visibleSize.copy(zLength = 0)
        )                                           // 8
        return player
    }

    companion object {
        fun create() = GameBuilder(
            worldSize = WORLD_SIZE
        ).buildGame()
    }
}