package views.fragment

import attributes.types.iconTile
import extensions.GameItem
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Fragment

class InventoryRowFragment(
    width: Int,
    item: GameItem
) : Fragment {
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
        }
}