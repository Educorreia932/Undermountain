package game

import entities.Player
import extensions.GameEntity
import world.World

data class Game(
    val world: World,
    val player: GameEntity<Player>
) {
    companion object {
        fun create(
            player: GameEntity<Player>,
            world: World
        ) = Game(
            world = world,
            player = player
        )
    }
}