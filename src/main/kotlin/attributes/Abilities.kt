package attributes

import enums.AbilityType
import org.hexworks.cobalt.core.api.UUID
import org.hexworks.cobalt.databinding.api.extension.toProperty
import org.hexworks.zircon.api.Components

class Abilities(
    var strength: Ability = Ability(10),
    dexterity: Ability = Ability(10),
    constitution: Ability = Ability(10),
    wisdom: Ability = Ability(10),
    intelligence: Ability = Ability(10),
    charisma: Ability = Ability(10)
) : DisplayableAttribute {
    override val id = UUID.randomUUID()

    private val abilities = mapOf(
        AbilityType.STR to strength,
        AbilityType.DEX to dexterity,
        AbilityType.CON to constitution,
        AbilityType.WIS to wisdom,
        AbilityType.INT to intelligence,
        AbilityType.CHA to charisma
    )

    override fun toComponent(width: Int) = Components.vbox()
        .withPreferredSize(width, 10)
        .build()
        .apply {
            addComponent(
                Components.textBox(width)
                    .addHeader("Attributes")
            )

            var index = 0

            for ((key, value) in abilities) {
                val label = Components.label()
                    .withPreferredSize(width, 1)
                    .build()

                label.textProperty.updateFrom(
                    "$key: ${value.getValue()} (${value.getModifier()})".toProperty()
                )
                // TODO: Add a plus if the number is positive.
                // TODO: Perhaps make the CreatureAttribute displayable itself

                addComponent(label)

                index++
            }
        }

    fun applyBonuses(bonuses: Set<AbilityBonus>) {
        bonuses.forEach { bonus ->
            abilities[bonus.abilityType]?.applyBonus(bonus)
        }
    }

    companion object {
        fun create(
            strength: Int,
            dexterity: Int,
            constitution: Int,
            wisdom: Int,
            intelligence: Int,
            charisma: Int
        ) = Abilities(
            Ability(strength),
            Ability(dexterity),
            Ability(constitution),
            Ability(wisdom),
            Ability(intelligence),
            Ability(charisma),
        )
    }
}