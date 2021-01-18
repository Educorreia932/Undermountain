package views

import kotlinx.serialization.json.Json
import org.hexworks.zircon.api.ColorThemes
import org.hexworks.zircon.api.ComponentDecorations
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.RadioButton
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
            ) // shadow can be added
            .withSize(32, 40)
            .withPosition(1, 1)
            .build()

        val header = Components.header()
            // corner of the panel
            .withPosition(1, 1)
            .withText("Race")
            .build()

        val race: RadioButtonGroup = Components.radioButtonGroup().build()

        val right: RadioButton = Components.radioButton()
            .withPosition(2, 3)
            .withKey("A")
            .withText("Human")
            .build()

        race.addComponents(right)

        panel.addComponents(header, right)

        screen.addComponent(panel)

        getRaces()
    }

    fun getRaces() {
        val lines = loadResource("/5e-SRD-Races.json")
        val obj = Json.parseToJsonElement(lines)

        println(obj)
    }

    private fun loadResource(file: String) = javaClass.getResource(file).readText()
}