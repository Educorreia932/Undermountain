package entities

import attributes.SpellStats
import extensions.AnyGameEntity
import extensions.GameEntity
import game.GameContext
import org.hexworks.amethyst.api.base.BaseEntityType
import org.hexworks.amethyst.api.entity.EntityType

interface Spell : EntityType

object Firebolt : BaseEntityType(
    name = "Firebolt",
    description = "Your hurl a mote of fire at a creature or object within range."
), Spell


val GameEntity<Spell>.effects: List<(context: GameContext, caster: AnyGameEntity, target: AnyGameEntity) -> Unit>
    get() = findAttribute(SpellStats::class).get().effects