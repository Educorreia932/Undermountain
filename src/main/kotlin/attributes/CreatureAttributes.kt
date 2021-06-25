package attributes

import org.hexworks.cobalt.core.api.UUID
import org.hexworks.cobalt.databinding.api.extension.createPropertyFrom
import org.hexworks.zircon.api.Components

enum class AttributeType {
    STR,
    DEX,
    CON,
    WIS,
    INT,
    CHA
}

class CreatureAttributes(
    strength: CreatureAttribute = CreatureAttribute(10),
    dexterity: CreatureAttribute = CreatureAttribute(10),
    constitution: CreatureAttribute = CreatureAttribute(10),
    wisdom: CreatureAttribute = CreatureAttribute(10),
    intelligence: CreatureAttribute = CreatureAttribute(10),
    charisma: CreatureAttribute = CreatureAttribute(10)
) : DisplayableAttribute {
    override val id = UUID.randomUUID()

    val attributes = mapOf(
        AttributeType.STR to strength,
        AttributeType.DEX to dexterity,
        AttributeType.CON to constitution,
        AttributeType.WIS to wisdom,
        AttributeType.INT to intelligence,
        AttributeType.CHA to charisma
    )

    override fun toComponent(width: Int) = Components.vbox()
        .withSize(width, 10)
        .build()
        .apply {
            addComponent(
                Components.textBox(width)
                    .addHeader("Attributes")
            )

            var index = 0

            for ((key, value) in attributes) {
                val label = Components.label()
                    .withSize(width, 1)
                    .build()

                label.textProperty.updateFrom(
                    createPropertyFrom("$key: ${value.getValue()} (${value.getModifier()})")
                )
                // TODO: Add a plus if the number is positive.
                // TODO: Perhaps make the CreatureAttribute displayable itself

                addComponent(label)

                index++
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
        ) = CreatureAttributes(
            CreatureAttribute(strength),
            CreatureAttribute(dexterity),
            CreatureAttribute(constitution),
            CreatureAttribute(wisdom),
            CreatureAttribute(intelligence),
            CreatureAttribute(charisma),
        )
    }
}