package attributes.classes

class Fighter(
    override val name: String = "Fighter",
    override val description: String = "A master of martial combat, skilled with a variety of weapons and armor",
    override val hitDie: Int = 10
) : Class() {
    
}