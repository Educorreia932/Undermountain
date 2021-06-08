package views.fragment

import attributes.Inventory
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Fragment

class InventoryFragment(
    inventory: Inventory,
    width: Int,
) : Fragment {
    override val root = Components.vbox()
        .withSize(width, inventory.size + 1)
        .build()
        .apply {
            inventory.items.forEach { item ->
                val row = InventoryRowFragment(width, item)
                addFragment(row)
            }
        }

    companion object {
        const val NAME_COLUMN_WIDTH = 15
    }
}