import game.GameConfig
import org.hexworks.zircon.api.ComponentDecorations.box
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.ColorTheme
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.view.base.BaseView

class PlayView(
    private val grid: TileGrid,
    theme: ColorTheme = GameConfig.THEME
) : BaseView(grid, theme) {

    init {
        val sidebar = Components.panel()
            .withSize(GameConfig.SIDEBAR_WIDTH, GameConfig.WINDOW_HEIGHT)
            .withDecorations(box())
            .build()

        screen.addComponent(sidebar)
    }
}