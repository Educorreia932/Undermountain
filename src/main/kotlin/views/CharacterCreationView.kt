package views

import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.ComponentDecorations
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.RadioButtonGroup
import org.hexworks.zircon.api.graphics.BoxType
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.view.base.BaseView

class CharacterCreationView(tileGrid: TileGrid) : BaseView(tileGrid) {
    override fun onDock() {
        screen.theme = ColorThemes.monokaiBlue()

        val panel = Components.panel()
            .withDecorations(
                ComponentDecorations.box(BoxType.SINGLE, "Character Creation"),
                ComponentDecorations.shadow()
            )
            .withSize(32, 40)
            .withPosition(1, 1)
            .build()

        val header = Components.header()
            .withPosition(1, 1)
            .withText("Race")
            .build()

        val raceGroup: RadioButtonGroup = Components.radioButtonGroup().build()

        panel.addComponents(header)

        screen.addComponent(panel)

        var y = 3
    }

}