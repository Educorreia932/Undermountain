import org.hexworks.zircon.api.application.AppConfig
import org.hexworks.zircon.api.CP437TilesetResources.aduDhabi16x16
import org.hexworks.zircon.api.SwingApplications.startTileGrid
import views.CharacterCreationView

fun main() {
    val tileGrid = startTileGrid(
        AppConfig.newBuilder()
            .withSize(100, 50)
            .withDefaultTileset(aduDhabi16x16())
            .build()
    )

    val view = CharacterCreationView(tileGrid)

    view.dock()
}