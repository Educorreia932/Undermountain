package attributes.races

import attributes.AbilityBonus
import enums.CreatureSize
import org.hexworks.amethyst.api.base.BaseAttribute

abstract class PlayerRace(
    name: String,
    description: String,
    speed: Int,
    size: CreatureSize,
    val abilityBonuses: Set<AbilityBonus>
// Starting proficiencies
// Languages
) : BaseAttribute()