package game

import entities.Player
import entities.Target
import extensions.GameEntity
import world.World

data class Game(
    val world: World,
    val player: GameEntity<Player>,
    val target: GameEntity<Target>
) {
    companion object {
        fun create(
            player: GameEntity<Player>,
            world: World,
            target: GameEntity<Target>
        ) = Game(
            world = world,
            player = player,
            target = target
        )
    }
}