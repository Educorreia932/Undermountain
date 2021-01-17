fun main() {
    val character = Player();
    character.experiencePoints = 5

    println(character.level)
    println(character.attributes[Attribute.STRENGTH])
}