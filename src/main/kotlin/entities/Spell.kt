package entities

import org.hexworks.amethyst.api.base.BaseEntityType

enum class SpellComponent {
    V,
    S,
    M
}

enum class MagicSchool {
    Abjuration,
    Conjuration,
    Divination,
    Enchantment,
    Evocation,
    Illusion,
    Necromancy,
    Transmutation
}

class Spell(name: String) : BaseEntityType(
    name = name
) {
    val level: Int = 0
    val school: MagicSchool = MagicSchool.Abjuration
    val castingTime: Int = 0
    val range: Int = 0
    val components: Array<SpellComponent> = arrayOf()
    val duration: Int = 0
}