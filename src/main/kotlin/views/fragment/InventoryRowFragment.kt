package views.fragment

import entities.CombatItem
import entities.iconTile
import extensions.GameItem
import extensions.whenTypeIs
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Fragment

class InventoryRowFragment(
    width: Int,
    item: GameItem
) : Fragment {
    val equipButton = Components.button()
        .withText("Equip")
        .build()
    
    override val root = Components.hbox()
        .withSpacing(1)
        .withSize(width, 1)
        .build()
        .apply {
            addComponent(Components.icon().withIcon(item.iconTile))
            addComponent(
                Components.label()
                    .withSize(InventoryFragment.NAME_COLUMN_WIDTH, 1)
                    .withText(item.name)
            )
            item.whenTypeIs<CombatItem> {
                addComponent(equipButton)
            }
        }
}