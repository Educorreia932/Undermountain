package views.fragment

import extensions.GameSpell
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Fragment

class SpellRowFragment(
    width: Int,
    spell: GameSpell
) : Fragment {
    override val root = Components.hbox()
        .withSpacing(1)
        .withSize(width, 1)
        .build()
        .apply {
            addComponent(
                Components.label()
                    .withSize(InventoryFragment.NAME_COLUMN_WIDTH, 1)
                    .withText(spell.name)
            )
        }
}