package views.fragment

import attributes.Inventory
import extensions.GameItem
import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Fragment
import org.hexworks.zircon.api.component.VBox
import org.hexworks.zircon.api.uievent.Processed

class InventoryFragment(
    inventory: Inventory,
    width: Int,
    private val onEquip: (GameItem) -> Maybe<GameItem>
) : Fragment {
    private val equipButton = Components.button()
        .withText("Equip")
        .build()
    
    override val root = Components.vbox()
        .withSize(width, inventory.size + 1)
        .build()
        .apply {
            val list = this

            addComponent(Components.hbox()
                .withSpacing(1)
                .withSize(width, 1)
                .build().apply {
                    addComponent(Components.label().withText("").withSize(1, 1))
                    addComponent(Components.header().withText("Name").withSize(NAME_COLUMN_WIDTH, 1))
                    addComponent(Components.header().withText("Actions").withSize(ACTIONS_COLUMN_WIDTH, 1))
                })
            
            inventory.items.forEach { item ->
                addRow(width, item, list)
            }
        }
    
    private fun addRow(width: Int, item: GameItem, list: VBox) {
        list.addFragment(InventoryRowFragment(width, item).apply { 
            equipButton.onActivated {
                onEquip(item).map { oldItem ->        
//                    list.removeComponent(this.root)
                    addRow(width, oldItem, list)
                }
                
                Processed
            }
        })
    }

    companion object {
        const val NAME_COLUMN_WIDTH = 15
        const val ACTIONS_COLUMN_WIDTH = 10
    }
}