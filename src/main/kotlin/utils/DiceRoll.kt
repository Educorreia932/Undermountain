package utils

class DiceRoll(
    private val quantity: Int,
    private val value: Int
) {
    fun roll(): Int {
        return ((0..quantity).map { (0..value).random() }).sum()
    }
}