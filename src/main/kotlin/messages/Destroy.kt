package messages

import extensions.GameEntity
import extensions.GameMessage
import game.GameContext
import org.hexworks.amethyst.api.entity.EntityType

data class Destroy(
    override val context: GameContext,
    override val source: GameEntity<EntityType>,
    val target: GameEntity<EntityType>
): GameMessage
