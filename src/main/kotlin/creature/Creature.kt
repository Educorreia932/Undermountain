package creature

open class Creature {
    var name: String
    var hitpoints: Int
    var speed: Int
    var ac: Int
    var alignment: Alignment

    var attributes: Map<Attributes, Int> = mapOf(
        Attributes.STRENGTH to 10,
        Attributes.DEXTERITY to 10,
        Attributes.CONSTITUTION to 10,
        Attributes.WISDOM to 10,
        Attributes.INTELLIGENCE to 10,
        Attributes.CHARISMA to 10,
    )

    // TODO: Languages
    // TODO: Actions

    constructor() {
        this.name = ""
        this.hitpoints = -1
        this.speed = -1
        this.ac = -1
        this.alignment = Alignment.UNALIGNED
    }

    constructor(name: String, hitpoints: Int, speed: Int, ac: Int, alignment: Alignment) {
        this.name = name
        this.hitpoints = hitpoints
        this.speed = speed
        this.ac = ac
        this.alignment = alignment
    }
}