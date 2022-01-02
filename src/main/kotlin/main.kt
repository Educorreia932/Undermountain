import attributes.classes.Sorcerer
import attributes.races.Dragonborn
import builders.CharacterBuilder
import builders.GameBuilder
import game.GameConfig
import org.hexworks.zircon.api.SwingApplications.startTileGrid
import views.PlayView

fun main() {
    val tileGrid = startTileGrid(GameConfig.buildAppConfig())

    // val view = CharacterCreationView(tileGrid)
    val player = CharacterBuilder()
        .withRace(Dragonborn())
        .withClass(Sorcerer())
        .withAttributes(10, 10, 10, 10, 10, 10)
        .build()

    val view = PlayView(tileGrid, game = GameBuilder.create(player))

    view.dock()
}

