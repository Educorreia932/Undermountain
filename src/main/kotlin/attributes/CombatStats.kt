package attributes

import org.hexworks.amethyst.api.base.BaseAttribute
import org.hexworks.cobalt.databinding.api.extension.createPropertyFrom
import org.hexworks.cobalt.databinding.api.property.Property

data class CombatStats(
    val maxHpProperty: Property<Int>,
    val currentHpProperty: Property<Int> = createPropertyFrom(maxHpProperty.value),
    val acProperty: Property<Int>
) : BaseAttribute() {
    val maxHp: Int by maxHpProperty.asDelegate()
    val currentHp: Int by currentHpProperty.asDelegate()
    val ac: Int by acProperty.asDelegate()

    companion object {
        fun create(maxHp: Int, currentHp: Int = maxHp, ac: Int) =
            CombatStats(
                maxHpProperty = createPropertyFrom(maxHp),
                currentHpProperty = createPropertyFrom(currentHp),
                acProperty = createPropertyFrom(ac)
            )
    }
}
