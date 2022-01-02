package builders

import entities.Player
import extensions.GameEntity
import extensions.position
import game.Game
import game.GameConfig
import game.GameConfig.LOG_AREA_HEIGHT
import game.GameConfig.SIDEBAR_WIDTH
import game.GameConfig.WINDOW_HEIGHT
import game.GameConfig.WINDOW_WIDTH
import game.GameConfig.WORLD_SIZE
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size3D

class GameBuilder(val worldSize: Size3D, val player: GameEntity<Player>) {
    private val visibleSize = Size3D.create(
        xLength = WINDOW_WIDTH - SIDEBAR_WIDTH,
        yLength = WINDOW_HEIGHT - LOG_AREA_HEIGHT,
        zLength = 1
    )

    private val world = WorldBuilder(worldSize)
        .makeCaves()
        .build(visibleSize = visibleSize)
    
    fun buildGame(): Game {
        prepareWorld()

        val player = addPlayer(player)
        
        // Add goblin
        world.addEntity(
            EntityFactory.newGoblin(),
            player.position.withRelativeY(-1)
        )

        // Add scimitar
        world.addEntity(
            EntityFactory.newScimitar(),
            player.position.withRelativeY(1)
        )
        
        world.addEntity(EntityFactory.newFogOfWar())

        return Game.create(
            player = player,
            world = world
        )
    }

    private fun prepareWorld() = also {
        world.scrollUpBy(world.actualSize.zLength)
    }

    private fun addPlayer(player: GameEntity<Player>): GameEntity<Player> {
        world.addAtEmptyPosition(
            player,
            offset = Position3D.create(0, 0, GameConfig.DUNGEON_LEVELS - 1),
            size = world.visibleSize.copy(zLength = 0)
        )

        return player
    }

    companion object {
        fun create(player: GameEntity<Player>) = GameBuilder(
            worldSize = WORLD_SIZE,
            player = player
        ).buildGame()
    }
}