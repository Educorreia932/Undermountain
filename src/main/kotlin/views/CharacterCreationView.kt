package views

import attributes.classes.Fighter
import attributes.races.Human
import builders.CharacterBuilder
import builders.GameBuilder
import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.ComponentDecorations
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.RadioButton
import org.hexworks.zircon.api.component.RadioButtonGroup
import org.hexworks.zircon.api.graphics.BoxType
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.view.base.BaseView

class CharacterCreationView(tileGrid: TileGrid) : BaseView(tileGrid) {
    init {
        screen.theme = ColorThemes.monokaiOrange()

        val panel = Components.panel()
            .withDecorations(
                ComponentDecorations.box(BoxType.SINGLE, "Character Creation"),
                ComponentDecorations.shadow()
            )
            .withSize(32, 40)
            .withPosition(1, 1)
            .build()

        val raceGroup: RadioButtonGroup = Components.radioButtonGroup().build()
        val radiobutton: RadioButton = Components.radioButton()
            .withText("Fighter")
            .withKey("Fighter")
            .build()

        raceGroup.addComponent(radiobutton)

        panel.addComponents(radiobutton)

        val readyButton = Components.button()
            .withText("Ready")
            .build()

        val player = CharacterBuilder()
            .withRace(Human())
            .withClass(Fighter())
            .withAttributes(10, 10, 10, 10, 10, 10)
            .build()
        
        readyButton.onActivated {
            replaceWith(PlayView(tileGrid, game = GameBuilder.create(player)))
        }

        screen.addComponents(panel, readyButton)
    }
}