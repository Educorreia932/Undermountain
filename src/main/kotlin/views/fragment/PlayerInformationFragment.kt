package views.fragment

import attributes.DisplayableAttribute
import attributes.classes.PlayerClass
import entities.Player
import entities.equippedWeapon
import extensions.GameEntity
import extensions.tryToFindAttribute
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Fragment

class PlayerInformationFragment(
    width: Int,
    player: GameEntity<Player>
) : Fragment {
    override val root = Components.vbox()
        .withSize(width, 30)
        .withSpacing(1)
        .build()
        .apply {
            addComponent(Components.header().withText(player.name))

            player.attributes.toList().filterIsInstance<DisplayableAttribute>()
                .forEach {
                    addComponent(it.toComponent(width))
                }
            
            addComponent(Components.header().withText("Equipped weapon"))
            addComponent(Components.textArea().withText(player.equippedWeapon.toString()))
        }
}