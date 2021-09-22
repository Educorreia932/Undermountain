package messages

import extensions.AnyGameEntity
import extensions.GameMessage
import game.GameContext
import org.hexworks.zircon.api.data.Position3D

data class InspectSpells(
    override val context: GameContext,
    override val source: AnyGameEntity,
    val position: Position3D
) : GameMessage