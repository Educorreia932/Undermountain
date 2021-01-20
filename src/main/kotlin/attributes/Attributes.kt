package attributes

import org.hexworks.cobalt.core.api.UUID
import org.hexworks.cobalt.databinding.api.extension.createPropertyFrom
import org.hexworks.zircon.api.Components

enum class AttributeType {
    Strength,
    Dexterity,
    Constitution,
    Wisdom,
    Intelligence,
    Charisma
}

class Attributes : DisplayableAttribute {
    override val id = UUID.randomUUID()

    val attributes: Map<AttributeType, Int> = mapOf(
        AttributeType.Strength to 10,
        AttributeType.Dexterity to 10,
        AttributeType.Constitution to 10,
        AttributeType.Wisdom to 10,
        AttributeType.Intelligence to 10,
        AttributeType.Charisma to 10
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
                    createPropertyFrom("$key: $value")
                )

                addComponent(label)

                index++
            }
        }
}