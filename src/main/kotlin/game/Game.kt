package game

import attributes.types.PlayerEntity
import extensions.GameEntity
import world.World

class Game(
    val world: World,
    val player: GameEntity<PlayerEntity>
) {
    companion object {
        fun create(
            player: GameEntity<PlayerEntity>,
            world: World
        ) = Game(
            world = world,
            player = player
        )
    }
}