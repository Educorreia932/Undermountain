package attributes

import org.hexworks.cobalt.core.api.UUID
import org.hexworks.cobalt.databinding.api.extension.createPropertyFrom
import org.hexworks.zircon.api.Components
import kotlin.math.floor

enum class AttributeType {
    STR,
    DEX,
    CON,
    WIS,
    INT,
    CHA
}

class Attributes : DisplayableAttribute {
    override val id = UUID.randomUUID()

    val attributes: Map<AttributeType, Int> = mapOf(
        AttributeType.STR to 10,
        AttributeType.DEX to 10,
        AttributeType.CON to 10,
        AttributeType.WIS to 10,
        AttributeType.INT to 10,
        AttributeType.CHA to 10
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
                    createPropertyFrom("$key: $value (${getModifier(key)})")
                )

                addComponent(label)

                index++
            }
        }

    private fun getModifier(attributeType: AttributeType): Int = floor((attributes[attributeType]!! - 10) / 2.0).toInt()
}