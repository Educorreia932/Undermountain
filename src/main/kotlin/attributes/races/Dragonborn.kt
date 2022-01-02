package attributes.races

import attributes.AbilityBonus
import enums.AbilityType
import enums.CreatureSize

class Dragonborn : PlayerRace(
    name = "Dragonborn",
    description = "Dragonborn look very much like dragons standing erect in humanoid form, though they lack wings or tail.",
    speed = 30,
    size = CreatureSize.Medium,
    abilityBonuses = setOf(
        AbilityBonus(AbilityType.STR, 2),
        AbilityBonus(AbilityType.CHA, 1),
    )
) 