package builders

import attributes.SpellStats
import entities.AcidSplash
import entities.Combatant
import entities.Firebolt
import entities.RayOfFrost
import enums.MagicSchool
import enums.SpellComponent
import extensions.whenTypeIs
import functions.logGameEvent
import utils.DiceRoll

object SpellFactory {
    fun newFirebolt() = EntityFactory.newGameEntityOfType(Firebolt) {
        attributes(
            SpellStats(
                level = 0,
                school = MagicSchool.Evocation,
                components = setOf(SpellComponent.V, SpellComponent.S),
                range = 120,
                duration = 0,
                effects = listOf { _, caster, target ->
                    val finalDamage = DiceRoll(1, 10).roll()

                    target.whenTypeIs<Combatant> {
                        logGameEvent("The fire bolt burns the $it for $finalDamage damage!", caster)
                    }
                }
            )
        )
    }

    fun newAcidSplash() = EntityFactory.newGameEntityOfType(AcidSplash) {
        attributes(
            SpellStats(
                level = 0,
                school = MagicSchool.Conjuration,
                components = setOf(SpellComponent.V, SpellComponent.S),
                range = 60,
                duration = 0,
                effects = listOf { _, caster, target ->
                    val finalDamage = DiceRoll(1, 6).roll()

                    target.whenTypeIs<Combatant> {
                        logGameEvent("The acid splash melts the $it for $finalDamage damage!", caster)
                    }
                }
            )
        )
    }

    fun newRayOfFrost() = EntityFactory.newGameEntityOfType(RayOfFrost) {
        attributes(
            SpellStats(
                level = 0,
                school = MagicSchool.Evocation,
                components = setOf(SpellComponent.V, SpellComponent.S),
                range = 60,
                duration = 0,
                effects = listOf { _, caster, target ->
                    val finalDamage = DiceRoll(1, 8).roll()

                    target.whenTypeIs<Combatant> {
                        logGameEvent("The ray of frost hurts the $it for $finalDamage damage!", caster)
                    }
                }
            )
        )
    }
}