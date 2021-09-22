package entities

import attributes.SpellStats
import extensions.AnyGameEntity
import extensions.GameEntity
import game.GameContext
import org.hexworks.amethyst.api.base.BaseEntityType
import org.hexworks.amethyst.api.entity.EntityType

interface Spell : EntityType

object AcidSplash : BaseEntityType(
    name = "Acid Splash",
    description = "You hurl a bubble of acid. Choose one creature within range, " +
            "or choose two creatures within range that are within 5 feet of each other." +
            "A target must succeed on a Dexterity saving throw or take 1d6 acid damage."
), Spell

object Firebolt : BaseEntityType(
    name = "Fire Bolt",
    description = "Your hurl a mote of fire at a creature or object within range."
), Spell

val GameEntity<Spell>.effects: List<(context: GameContext, caster: AnyGameEntity, target: AnyGameEntity) -> Unit>
    get() = findAttribute(SpellStats::class).get().effects