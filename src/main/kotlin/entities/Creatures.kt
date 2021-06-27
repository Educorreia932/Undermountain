package entities

import org.hexworks.amethyst.api.base.BaseEntityType

open class Creature(name: String): BaseEntityType(
    name = name
), Combatant

object Player : Creature(
    name = "Player"
), ExperienceGainer, ItemHolder, EquipmentHolder

object Goblin : Creature(
    name = "Goblin"
)