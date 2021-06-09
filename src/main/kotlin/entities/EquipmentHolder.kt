package entities

import attributes.Equipment
import attributes.Inventory
import extensions.GameCombatItem
import extensions.GameEquipmentHolder
import org.hexworks.amethyst.api.entity.EntityType

interface EquipmentHolder : EntityType

fun GameEquipmentHolder.equip(inventory: Inventory, item: GameCombatItem): GameCombatItem {
    return equipment.equip(inventory, item)
}

val GameEquipmentHolder.equipment: Equipment
    get() = findAttribute(Equipment::class).get()