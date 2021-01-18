import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import player.Player
import views.CharacterCreationView.Companion.loadResource

fun main() {
//    val tileGrid = startTileGrid(
//        AppConfig.newBuilder()
//            .withSize(100, 50)
//            .withDefaultTileset(aduDhabi16x16())
//            .build()
//    )
//
//    val view = CharacterCreationView(tileGrid)
//
//    view.dock()

    val races: JsonArray = Json.parseToJsonElement(loadResource("/5e-SRD-Races.json")) as JsonArray
    var player = Player(races[0] as JsonObject, null)
}

