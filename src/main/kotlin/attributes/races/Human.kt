package attributes.races

import attributes.AbilityBonus
import enums.AbilityType
import enums.CreatureSize

class Human : PlayerRace(
    name = "Human",
    description = "Humans are the most adaptable and ambitious people among the common races.",
    speed = 30,
    size = CreatureSize.Medium,
    abilityBonuses = setOf(
        AbilityBonus(AbilityType.STR, 1),
        AbilityBonus(AbilityType.DEX, 1),
        AbilityBonus(AbilityType.CON, 1),
        AbilityBonus(AbilityType.WIS, 1),
        AbilityBonus(AbilityType.INT, 1),
        AbilityBonus(AbilityType.CHA, 1),
    )
) 