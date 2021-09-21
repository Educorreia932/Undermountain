package views

import events.GameLogEvent
import game.Game
import game.GameConfig
import game.GameConfig.LOG_AREA_HEIGHT
import game.GameConfig.SIDEBAR_WIDTH
import game.GameConfig.WINDOW_HEIGHT
import game.GameConfig.WINDOW_WIDTH
import game.GameTileRepository
import org.hexworks.cobalt.databinding.api.extension.toProperty
import org.hexworks.cobalt.events.api.KeepSubscription
import org.hexworks.cobalt.events.api.subscribeTo
import org.hexworks.zircon.api.ComponentDecorations.box
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.ColorTheme
import org.hexworks.zircon.api.component.ComponentAlignment
import org.hexworks.zircon.api.game.ProjectionMode
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEvent
import org.hexworks.zircon.api.uievent.KeyboardEventType
import org.hexworks.zircon.api.uievent.MouseEventType
import org.hexworks.zircon.api.uievent.Processed
import org.hexworks.zircon.api.view.base.BaseView
import org.hexworks.zircon.internal.Zircon
import org.hexworks.zircon.internal.game.impl.GameAreaComponentRenderer
import views.fragment.PlayerInformationFragment

class PlayView(
    grid: TileGrid,
    game: Game,
    theme: ColorTheme = GameConfig.THEME,
) : BaseView(grid, theme) {
    init {
        val sidebar = Components.panel()
            .withSize(SIDEBAR_WIDTH, WINDOW_HEIGHT)
            .withDecorations(box())
            .build()

        sidebar.addFragment(
            PlayerInformationFragment(
                width = sidebar.contentSize.width,
                player = game.player
            )
        )

        val logArea = Components.logArea()
            .withDecorations(box(title = "Log"))
            .withSize(WINDOW_WIDTH - SIDEBAR_WIDTH, LOG_AREA_HEIGHT)
            .withAlignmentWithin(screen, ComponentAlignment.BOTTOM_RIGHT)
            .build()

        val gameComponent = Components.panel()
            .withSize(game.world.visibleSize.to2DSize())
            .withComponentRenderer(
                GameAreaComponentRenderer(
                    gameArea = game.world,
                    projectionMode = ProjectionMode.TOP_DOWN.toProperty(),
                    fillerTile = GameTileRepository.FLOOR
                )
            )
            .withAlignmentWithin(screen, ComponentAlignment.TOP_RIGHT)
            .build()

        screen.addComponents(sidebar, logArea, gameComponent)

        screen.handleKeyboardEvents(KeyboardEventType.KEY_PRESSED) { event, _ ->
            game.world.update(screen, event, game)
            Processed
        }
        
        screen.handleMouseEvents(MouseEventType.MOUSE_CLICKED) { event, _ ->
            game.world.update(screen, event, game)
            Processed
        }
        
        Zircon.eventBus.subscribeTo<GameLogEvent> { (text) ->
            logArea.addParagraph(
                paragraph = text,
                withNewLine = false,
                withTypingEffectSpeedInMs = 10
            )
            
            KeepSubscription
        }
        
        // Update the world, before the player presses any key
        game.world.update(
            screen,
            KeyboardEvent(
                type = KeyboardEventType.KEY_TYPED,
                key = "",
                code = KeyCode.DEAD_GRAVE
            ), game
        )
    }
}