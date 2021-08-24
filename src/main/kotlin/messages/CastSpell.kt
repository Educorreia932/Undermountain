package messages

import entities.Combatant
import entities.Spell
import extensions.GameEntity
import game.GameContext
import org.hexworks.amethyst.api.entity.EntityType

data class CastSpell(
    override val context: GameContext,
    override val source: GameEntity<Combatant>,
    override val target: GameEntity<EntityType>,
    val spell: GameEntity<Spell>
) : EntityAction<EntityType, EntityType>
