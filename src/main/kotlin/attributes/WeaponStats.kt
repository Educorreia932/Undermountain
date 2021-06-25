package attributes

import org.hexworks.amethyst.api.base.BaseAttribute
import utils.DiceRoll

data class WeaponStats(
    val damage: DiceRoll,
    val damageType: DamageType
) : BaseAttribute()