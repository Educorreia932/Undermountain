package systems

import attributes.KnownSpells
import game.GameConfig
import game.GameContext
import messages.InspectSpells
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.zircon.api.ComponentDecorations
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.builder.component.ModalBuilder
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.data.Size
import org.hexworks.zircon.api.uievent.Processed
import org.hexworks.zircon.internal.component.modal.EmptyModalResult
import views.fragment.KnownSpellsFragment

object Spellcaster : BaseFacet<GameContext, InspectSpells>(InspectSpells::class) {
    private val DIALOG_SIZE = Size.create(35, 15)

    override suspend fun receive(message: InspectSpells): Response {
        val (context, spellcaster, _) = message

        val screen = context.screen

        val panel = Components.panel()
            .withSize(DIALOG_SIZE)
            .withDecorations(ComponentDecorations.box(title = "Known Spells"), ComponentDecorations.shadow())
            .build()

        val fragment = KnownSpellsFragment(
            spellcaster.findAttribute(KnownSpells::class).get(),
            DIALOG_SIZE.width - 3,
        )
        
        panel.addFragment(fragment)

        val modal = ModalBuilder.newBuilder<EmptyModalResult>()
            .withParentSize(screen.size)
            .withComponent(panel)
            .withCenteredDialog(true)
            .build()

        panel.addComponent(Components.button()
            .withText("Close")
            .withAlignmentWithin(panel, ComponentAlignment.BOTTOM_LEFT)
            .build().apply {
                onActivated {
                    modal.close(EmptyModalResult)
                    Processed
                }
            })

        modal.theme = GameConfig.THEME
        screen.openModal(modal)
        
        return Consumed
    }
}