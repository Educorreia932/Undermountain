package attributes

import org.hexworks.amethyst.api.base.BaseAttribute

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

class SpellStats(
    val school: MagicSchool
) : BaseAttribute() {
    
}