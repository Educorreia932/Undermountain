package attributes

import org.hexworks.amethyst.api.base.BaseAttribute
import org.hexworks.cobalt.databinding.api.extension.toProperty
import org.hexworks.cobalt.databinding.api.property.Property

data class Stats(
    val maxHpProperty: Property<Int>,
    val currentHpProperty: Property<Int> = maxHpProperty.value.toProperty(),
    val acProperty: Property<Int>,
) : BaseAttribute() {
    val maxHp: Int by maxHpProperty.asDelegate()
    var currentHp: Int by currentHpProperty.asDelegate()
    val ac: Int by acProperty.asDelegate()
//    val size: CreatureSize
//    val alignment: Alignment

    companion object {
        fun create(maxHp: Int, currentHp: Int = maxHp, ac: Int) =
            Stats(
                maxHpProperty = maxHp.toProperty(),
                currentHpProperty = currentHp.toProperty(),
                acProperty = ac.toProperty(),
            )
    }
}
