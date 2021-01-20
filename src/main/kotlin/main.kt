
import game.GameConfig.WINDOW_HEIGHT
import game.GameConfig.WINDOW_WIDTH
import org.hexworks.zircon.api.CP437TilesetResources.aduDhabi16x16
import org.hexworks.zircon.api.SwingApplications.startTileGrid
import org.hexworks.zircon.api.application.AppConfig
import views.PlayView

fun main() {
    val tileGrid = startTileGrid(
        AppConfig.newBuilder()
            .withSize(WINDOW_WIDTH, WINDOW_HEIGHT)
            .withDefaultTileset(aduDhabi16x16())
            .build()
    )

    val view = PlayView(tileGrid)

    view.dock()
}

