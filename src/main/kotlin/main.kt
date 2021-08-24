import attributes.classes.Fighter
import attributes.races.Human
import builders.CharacterBuilder
import builders.GameBuilder
import game.GameConfig.WINDOW_HEIGHT
import game.GameConfig.WINDOW_WIDTH
import org.hexworks.zircon.api.CP437TilesetResources
import org.hexworks.zircon.api.SwingApplications.startTileGrid
import org.hexworks.zircon.api.application.AppConfig
import views.PlayView

fun main() {
    val tileGrid = startTileGrid(
        AppConfig.newBuilder()
            .withSize(WINDOW_WIDTH, WINDOW_HEIGHT)
            .withDefaultTileset(CP437TilesetResources.kelora16x16())
            .build()
    )

    // val view = CharacterCreationView(tileGrid)
    val player = CharacterBuilder()
        .withRace(Human())
        .withClass(Fighter())
        .withAttributes(10, 10, 10, 10, 10, 10)
        .build()
    
    player.attributes
    
    val view = PlayView(tileGrid, game = GameBuilder.create(player))

    view.dock()
}

