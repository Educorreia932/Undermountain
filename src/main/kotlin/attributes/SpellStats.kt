package attributes

import enums.MagicSchool
import enums.SpellComponent
import org.hexworks.amethyst.api.base.BaseAttribute

class SpellStats(
    val level: Int,
    val school: MagicSchool,
    val component: Set<SpellComponent>
    // val range
    // val duration
) : BaseAttribute()