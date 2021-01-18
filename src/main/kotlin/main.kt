import org.hexworks.zircon.api.application.AppConfig
import org.hexworks.zircon.api.CP437TilesetResources.aduDhabi16x16
import org.hexworks.zircon.api.SwingApplications.startTileGrid


fun main() {
    val tileGrid = startTileGrid(
        AppConfig.newBuilder()
            .withSize(34, 18)
            .withDefaultTileset(aduDhabi16x16())
            .build()
    )

    val view = GameView(tileGrid)

    view.dock()
}