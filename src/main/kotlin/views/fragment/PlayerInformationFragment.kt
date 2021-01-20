package views.fragment

import attributes.DisplayableAttribute
import attributes.Player
import extensions.GameEntity
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
        }

}