package creature

open class Creature {
    var name: String = ""
    var hitpoints: Int = 0
    var speed: Int = 0
    var ac: Int = 0
    val alignment: Alignment = Alignment.UNALIGNED

    var attributes: Map<Attribute, Int> = mapOf(
        Attribute.STRENGTH to 10,
        Attribute.DEXTERITY to 10,
        Attribute.CONSTITUTION to 10,
        Attribute.WISDOM to 10,
        Attribute.INTELLIGENCE to 10,
        Attribute.CHARISMA to 10,
    )

    // TODO: Languages
    // TODO: Actions
}