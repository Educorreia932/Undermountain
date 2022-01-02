package entities

import attributes.SpellStats
import extensions.GameEntity
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

object RayOfFrost : BaseEntityType(
    name = "Ray of Frost",
    description = "A frigid beam of blue-white light streaks toward a creature within range. " +
            "Make a ranged spell attack against the target. " +
            "On a hit, it takes 1d8 cold damage, and its speed is reduced by 10 feet until the start of your next turn."
), Spell

val GameEntity<Spell>.effects
    get() = findAttribute(SpellStats::class).get().effects