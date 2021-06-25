package utils

class DiceRoll(
    private val quantity: Int,
    private val value: Int
) {
    fun roll(): Int {
        return ((0 until quantity).map { (1..value).random() }).sum()
    }
}