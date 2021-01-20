package attributes

enum class Component {
    V,
    S,
    M
}

enum class MagicSchool {
    ABJURATION,
    CONJURATION,
    DIVINATION,
    ENCHANTMENT,
    EVOCATION,
    ILLUSION,
    NECROMANCY,
    TRANSMUTATION
}

class Spell {
    val level: Int = 0
    val school: MagicSchool = MagicSchool.ABJURATION
    // TODO: Casting time
    val range: Int = 0
    val components: Array<Component> = arrayOf()
    // TODO: Duration
}