package game

import entities.Player
import extensions.GameEntity
import org.hexworks.amethyst.api.Context
import org.hexworks.zircon.api.screen.Screen
import world.World

data class GameContext(
    val world: World,
    val screen: Screen,
    val uiEvent: org.hexworks.zircon.api.uievent.UIEvent,
    val player: GameEntity<Player>
) : Context
