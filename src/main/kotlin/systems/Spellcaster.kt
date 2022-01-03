package systems

import attributes.KnownSpells
import builders.EntityFactory.newTarget
import enums.GameState
import extensions.position
import game.GameConfig
import game.GameContext
import game.MetaContext
import messages.CastSpell
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
            .withPreferredSize(DIALOG_SIZE)
            .withDecorations(ComponentDecorations.box(title = "Known Spells"), ComponentDecorations.shadow())
            .build()

        val fragment = KnownSpellsFragment(
            spellcaster.findAttribute(KnownSpells::class).get(),
            DIALOG_SIZE.width - 3,
            onCast = { spell ->
                MetaContext.gameState = GameState.TARGETING
                MetaContext.suspendedAction = CastSpell(context, spellcaster, spellcaster, spell)
                spell
            }
        )
        
        panel.addFragment(fragment)
        
        val modal = ModalBuilder.newBuilder<EmptyModalResult>()
            .withPreferredSize(screen.size)
            .withComponent(panel)
            .withCenteredDialog(true)
            .withTileset(GameConfig.TILESET)
            .withColorTheme(GameConfig.THEME)
            .build()
        
        fragment.rows.forEach {
            apply { 
                it.castButton.apply { 
                    onActivated { 
                        modal.close(EmptyModalResult) 
                        Processed
                    }
                }
            }
        }

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