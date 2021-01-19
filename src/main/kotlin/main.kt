
import data.Data
import data.DataType
import game.GameConfig.WINDOW_HEIGHT
import game.GameConfig.WINDOW_WIDTH
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import org.hexworks.zircon.api.CP437TilesetResources.aduDhabi16x16
import org.hexworks.zircon.api.SwingApplications.startTileGrid
import org.hexworks.zircon.api.application.AppConfig
import player.Player
import views.PlayView

fun main() {
    val tileGrid = startTileGrid(
        AppConfig.newBuilder()
            .withSize(WINDOW_WIDTH, WINDOW_HEIGHT)
            .withDefaultTileset(aduDhabi16x16())
            .build()
    )

    //val view = CharacterCreationView(tileGrid)
    val view = PlayView(tileGrid)

    view.dock()

    val raceData: JsonArray = Data.data[DataType.Race]!!
    val classData: JsonArray = Data.data[DataType.Class]!!

    var player = Player(raceData[0] as JsonObject, classData[0] as JsonObject)
}

