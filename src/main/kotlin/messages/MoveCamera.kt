package messages

import extensions.GameEntity
import extensions.GameMessage
import game.GameContext
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.data.Position3D

data class MoveCamera(
    override val context: GameContext,
    override val source: GameEntity<EntityType>,
    val previousPosition: Position3D
) : GameMessage
