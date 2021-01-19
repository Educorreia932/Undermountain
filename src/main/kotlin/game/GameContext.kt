package game

import attributes.types.PlayerEntity
import extensions.GameEntity
import org.hexworks.amethyst.api.Context
import org.hexworks.zircon.api.screen.Screen
import org.w3c.dom.events.UIEvent
import world.World

data class GameContext(
    val world: World,
    val screen: Screen,
    val uiEvent: UIEvent,
    val player: GameEntity<PlayerEntity>
) : Context
