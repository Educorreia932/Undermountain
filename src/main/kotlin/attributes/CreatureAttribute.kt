package attributes

import kotlin.math.floor

class CreatureAttribute(
    private val base: Int,
    private val bonus: Int = 0
) {
    fun getValue(): Int =
        base
    
    fun getModifier(): Int = 
        floor((getValue() - 10) / 2.0).toInt()
}