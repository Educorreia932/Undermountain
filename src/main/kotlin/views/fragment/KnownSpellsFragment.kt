package views.fragment

import attributes.KnownSpells
import extensions.GameSpell
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Fragment
import org.hexworks.zircon.api.component.VBox
import org.hexworks.zircon.api.uievent.Processed

class KnownSpellsFragment(
    knownSpells: KnownSpells,
    width: Int
) : Fragment {
    override val root = Components.vbox()
        .withSize(width, knownSpells.size + 1)
        .build()
        .apply {
            addComponent(Components.hbox()
                .withSpacing(1)
                .withSize(width, 1)
                .build().apply {
                    addComponent(Components.label().withText("").withSize(1, 1))
                    addComponent(Components.header().withText("Name").withSize(NAME_COLUMN_WIDTH, 1))
                }
            )

            knownSpells.currentSpells.forEach() { item ->
                addRow(width, item, this)
            }
        }

    private fun addRow(width: Int, spell: GameSpell, list: VBox) {
        list.addFragment(SpellRowFragment(width, spell))
    }

    companion object {
        const val NAME_COLUMN_WIDTH = 15
    }
}