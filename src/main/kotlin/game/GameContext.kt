package game

import entities.Player
import entities.Target
import extensions.GameEntity
import org.hexworks.amethyst.api.Context
import org.hexworks.zircon.api.screen.Screen
import org.hexworks.zircon.api.uievent.UIEvent
import world.World

data class GameContext(
    val world: World,
    val screen: Screen,
    val uiEvent: UIEvent,
    val player: GameEntity<Player>,
    val target: GameEntity<Target>
) : Context
