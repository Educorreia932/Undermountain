package views

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
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

        for (raceElement in getRaces()) {
            val raceName = (raceElement as JsonObject)["name"].toString()
            val raceButton: RadioButton = Components.radioButton()
                .withPosition(2, y)
                .withKey(raceName)
                .withText(raceName)
                .build()

            raceGroup.addComponents(raceButton)
            panel.addComponent(raceButton)

            y += 1
        }
    }

    fun getRaces(): JsonArray {
        val lines: String = loadResource("/5e-SRD-Races.json")

        return Json.parseToJsonElement(lines) as JsonArray
    }

    companion object {
        fun loadResource(file: String): String = javaClass.getResource(file).readText()
    }
}