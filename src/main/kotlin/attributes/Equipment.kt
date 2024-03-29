package attributes

import builders.EntityFactory.newShortsword
import entities.Weapon
import extensions.GameCombatItem
import extensions.GameEntity
import extensions.whenTypeIs
import org.hexworks.amethyst.api.base.BaseAttribute
import org.hexworks.cobalt.databinding.api.extension.toProperty
import org.hexworks.cobalt.databinding.api.property.Property

class Equipment(initialWeapon: GameEntity<Weapon> = newShortsword()): BaseAttribute() {
    private val weaponProperty: Property<GameEntity<Weapon>> = initialWeapon.toProperty()

    val weapon: GameEntity<Weapon> by weaponProperty.asDelegate()
    
    fun equip(inventory: Inventory, combatItem: GameCombatItem): GameCombatItem {
        combatItem.whenTypeIs<Weapon> {
            return equipWeapon(inventory, it)
        }

        throw IllegalStateException("Combat item is not Weapon or Armor.")
    }
    
    private fun equipWeapon(inventory: Inventory, newWeapon: GameEntity<Weapon>): GameCombatItem {
        val oldWeapon = weapon
        
        inventory.removeItem(newWeapon)
        inventory.addItem(oldWeapon)
        weaponProperty.value = newWeapon
        
        return oldWeapon
    }
}