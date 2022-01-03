package views.fragment

import extensions.GameSpell
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Fragment

class SpellRowFragment(
    width: Int,
    spell: GameSpell
) : Fragment {
    val castButton = Components.button()
        .withText("Cast")
        .build()

    override val root = Components.hbox()
        .withSpacing(1)
        .withPreferredSize(width, 1)
        .build()
        .apply {
            addComponent(
                Components.label()
                    .withPreferredSize(InventoryFragment.NAME_COLUMN_WIDTH, 1)
                    .withText(spell.name)
            )
            
            addComponent(castButton)
        }
}