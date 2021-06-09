package messages

import extensions.GameItemHolder
import extensions.GameMessage
import game.GameContext
import org.hexworks.zircon.api.data.Position3D

data class PickItemUp(
    override val context: GameContext,
    override val source: GameItemHolder,
    val position: Position3D
) : GameMessage