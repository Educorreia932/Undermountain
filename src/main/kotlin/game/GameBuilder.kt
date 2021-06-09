package game

import builders.EntityFactory
import builders.WorldBuilder
import entities.Monster
import entities.Player
import extensions.GameEntity
import extensions.GameItem
import game.GameConfig.LOG_AREA_HEIGHT
import game.GameConfig.SIDEBAR_WIDTH
import game.GameConfig.WINDOW_HEIGHT
import game.GameConfig.WINDOW_WIDTH
import game.GameConfig.WORLD_SIZE
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.data.Size3D

class GameBuilder(val worldSize: Size3D) {
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

        val player = addPlayer()
        val monster = addMonster()
        val sword = addSword()

        return Game.create(
            player = player,
            world = world
        )
    }

    private fun prepareWorld() = also {
        world.scrollUpBy(world.actualSize.zLength)
    }

    private fun addPlayer(): GameEntity<Player> {
        val player = EntityFactory.newPlayer(0, 0)

        world.addAtEmptyPosition(
            player,
            offset = Position3D.create(0, 0, GameConfig.DUNGEON_LEVELS - 1), 
            size = world.visibleSize.copy(zLength = 0)
        )

        return player
    }

    private fun addMonster(): GameEntity<Monster> {
        val monster = EntityFactory.newMonster()

        world.addAtEmptyPosition(
            monster,
            offset = Position3D.create(0, 0, GameConfig.DUNGEON_LEVELS - 1), 
            size = world.visibleSize.copy(zLength = 0)
        )

        return monster
    }
    
    private fun addSword(): GameItem {
        val item = EntityFactory.newSword()
        
        world.addAtEmptyPosition(
            item,
            offset = Position3D.create(0, 0, GameConfig.DUNGEON_LEVELS - 1), 
            size = world.visibleSize.copy(zLength = 0)
        )
        
        return item
    }

    companion object {
        fun create() = GameBuilder(
            worldSize = WORLD_SIZE
        ).buildGame()
    }
}