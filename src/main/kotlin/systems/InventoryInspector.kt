package systems

import entities.CombatItem
import entities.EquipmentHolder
import entities.equip
import entities.inventory
import extensions.GameItem
import extensions.whenTypeIs
import game.GameConfig
import game.GameContext
import messages.InspectInventory
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet
import org.hexworks.cobalt.datatypes.Maybe
import org.hexworks.zircon.api.ComponentDecorations.box
import org.hexworks.zircon.api.ComponentDecorations.shadow
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.builder.component.ModalBuilder
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.data.Size
import org.hexworks.zircon.api.uievent.Processed
import org.hexworks.zircon.internal.component.modal.EmptyModalResult
import views.fragment.InventoryFragment

object InventoryInspector : BaseFacet<GameContext, InspectInventory>(InspectInventory::class) {
    private val DIALOG_SIZE = Size.create(35, 15)

    override suspend fun receive(message: InspectInventory): Response {
        val (context, itemHolder, _) = message

        val screen = context.screen

        val panel = Components.panel()
            .withSize(DIALOG_SIZE)
            .withDecorations(box(title = "Inventory"), shadow())
            .build()

        val fragment = InventoryFragment(
            itemHolder.inventory,
            DIALOG_SIZE.width - 3,
            onEquip = { item ->
                var result = Maybe.empty<GameItem>()
                
                itemHolder.whenTypeIs<EquipmentHolder> { equipmentHolder ->
                    item.whenTypeIs<CombatItem> { combatItem ->
                        result = Maybe.of(equipmentHolder.equip(itemHolder.inventory, combatItem))
                    }
                }
                
                result
            }
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