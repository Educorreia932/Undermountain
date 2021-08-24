package attributes

import enums.MagicSchool
import enums.SpellComponent
import extensions.AnyGameEntity
import game.GameContext
import org.hexworks.amethyst.api.base.BaseAttribute

class SpellStats(
    val level: Int,
    val school: MagicSchool,
    val components: Set<SpellComponent>,
    val range: Int,
    val duration: Int,
    val effects: List<(context: GameContext, caster: AnyGameEntity, target: AnyGameEntity) -> Unit>  
) : BaseAttribute()