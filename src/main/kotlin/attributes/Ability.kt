package attributes

import kotlin.math.floor

class Ability(
    private val base: Int,
    private val bonuses: MutableSet<AbilityBonus> = mutableSetOf()
) {
    fun getValue(): Int =
        base + bonuses.sumOf { it.value }

    fun getModifier(): Int =
        floor((getValue() - 10) / 2.0).toInt()
    
    fun applyBonus(bonus: AbilityBonus) {
        bonuses.add(bonus)
    }
}