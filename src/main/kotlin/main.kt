import attributes.classes.Fighter
import attributes.races.Human
import builders.CharacterBuilder
import builders.GameBuilder
import game.GameConfig
import org.hexworks.zircon.api.SwingApplications
import org.hexworks.zircon.api.SwingApplications.startTileGrid
import views.PlayView

fun main() {
    val tileGrid = startTileGrid(GameConfig.buildAppConfig())

    // val view = CharacterCreationView(tileGrid)
    val player = CharacterBuilder()
        .withRace(Human())
        .withClass(Fighter())
        .withAttributes(10, 10, 10, 10, 10, 10)
        .build()

    val view = PlayView(tileGrid, game = GameBuilder.create(player))

    view.dock()
}

