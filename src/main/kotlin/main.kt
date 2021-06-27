
import game.GameConfig.WINDOW_HEIGHT
import game.GameConfig.WINDOW_WIDTH
import org.hexworks.zircon.api.CP437TilesetResources
import org.hexworks.zircon.api.SwingApplications.startTileGrid
import org.hexworks.zircon.api.application.AppConfig
import views.CharacterCreationView

fun main() {
    val tileGrid = startTileGrid(
        AppConfig.newBuilder()
            .withSize(WINDOW_WIDTH, WINDOW_HEIGHT)
            .withDefaultTileset(CP437TilesetResources.kelora16x16())
            .build()
    )

    val view = CharacterCreationView(tileGrid)

    view.dock()
}

