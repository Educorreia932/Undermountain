
import game.GameConfig.WINDOW_HEIGHT
import game.GameConfig.WINDOW_WIDTH
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import org.hexworks.zircon.api.CP437TilesetResources.aduDhabi16x16
import org.hexworks.zircon.api.SwingApplications.startTileGrid
import org.hexworks.zircon.api.application.AppConfig
import player.Player
import views.CharacterCreationView.Companion.loadResource
import views.PlayView

fun main() {
    val tileGrid = startTileGrid(
        AppConfig.newBuilder()
            .withSize(WINDOW_WIDTH, WINDOW_HEIGHT)
            .withDefaultTileset(aduDhabi16x16())
            .build()
    )

//    val view = CharacterCreationView(tileGrid)
    val view = PlayView(tileGrid)

    view.dock()

    val races: JsonArray = Json.parseToJsonElement(loadResource("/5e-SRD-Races.json")) as JsonArray
    val classes: JsonArray = Json.parseToJsonElement(loadResource("/5e-SRD-Classes.json")) as JsonArray
    var player = Player(races[0] as JsonObject, classes[0] as JsonObject)
}

