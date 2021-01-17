import org.hexworks.zircon.api.CP437TilesetResources.rexPaint16x16
import org.hexworks.zircon.api.SwingApplications.startTileGrid
import org.hexworks.zircon.api.application.AppConfig
import org.hexworks.zircon.api.color.ANSITileColor
import org.hexworks.zircon.api.data.Position
import org.hexworks.zircon.api.data.Tile

fun main() {
    val tileGrid = startTileGrid(
        AppConfig.newBuilder()
            .withSize(10, 10)
            .withDefaultTileset(rexPaint16x16())
            .build()
    )

    tileGrid.draw(
        Tile.newBuilder()
            .withBackgroundColor(ANSITileColor.CYAN)
            .withForegroundColor(ANSITileColor.WHITE)
            .withCharacter('x')
            .build(),
        Position.create(2, 3));
}