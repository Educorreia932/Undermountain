package messages

import entities.Spell
import extensions.GameEntity
import game.GameContext
import org.hexworks.amethyst.api.entity.EntityType

data class CastSpell(
    override val context: GameContext,
    override val source: GameEntity<EntityType>,
    override var target: GameEntity<EntityType>,
    val spell: GameEntity<Spell>
) : EntityAction<EntityType, EntityType>
