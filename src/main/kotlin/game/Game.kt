package game

import builders.WorldBuilder
import game.GameConfig.GAME_AREA_SIZE
import game.GameConfig.WORLD_SIZE
import org.hexworks.zircon.api.data.Size3D
import world.World

class Game(val world: World) {
    companion object {
        fun create(
            worldSize: Size3D = WORLD_SIZE,
            visibleSize: Size3D = GAME_AREA_SIZE
        ) = Game(
            WorldBuilder(worldSize)
                .makeCaves()
                .build(visibleSize)
        )
    }
}