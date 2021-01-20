package attributes

import org.hexworks.amethyst.api.base.BaseAttribute
import org.hexworks.cobalt.databinding.api.extension.createPropertyFrom
import org.hexworks.cobalt.databinding.api.property.Property

enum class CreatureSize {
    Tiny,
    Small,
    Medium,
    Large,
    Huge,
    Gargantuan
}

enum class Alignment {
    Unaligned,
    LawfulGood,
    NeutralGood,
    ChaoticGood,
    LawfulNeutral,
    Neutral,
    LawfulEvil,
    NeutralEvil,
    ChaoticEvil
}

data class Stats(
    val maxHpProperty: Property<Int>,
    val currentHpProperty: Property<Int> = createPropertyFrom(maxHpProperty.value),
    val acProperty: Property<Int>
) : BaseAttribute() {
    val maxHp: Int by maxHpProperty.asDelegate()
    val currentHp: Int by currentHpProperty.asDelegate()
    val ac: Int by acProperty.asDelegate()
//    val size: CreatureSize
//    val alignment: Alignment

    companion object {
        fun create(maxHp: Int, currentHp: Int = maxHp, ac: Int) =
            Stats(
                maxHpProperty = createPropertyFrom(maxHp),
                currentHpProperty = createPropertyFrom(currentHp),
                acProperty = createPropertyFrom(ac)
            )
    }
}
