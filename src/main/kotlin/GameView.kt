import org.hexworks.zircon.api.ComponentDecorations
import org.hexworks.zircon.api.Components
import org.hexworks.zircon.api.component.Button
import org.hexworks.zircon.api.component.CheckBox
import org.hexworks.zircon.api.data.Position
import org.hexworks.zircon.api.graphics.BoxType
import org.hexworks.zircon.api.grid.TileGrid
import org.hexworks.zircon.api.view.base.BaseView

class GameView(tileGrid: TileGrid) : BaseView(tileGrid) {
    override fun onDock() {
        val panel = Components.panel()
            .withDecorations( // panels can be wrapped in a box
                ComponentDecorations.box(BoxType.SINGLE, "Panel"),
                ComponentDecorations.shadow()
            ) // shadow can be added
            .withSize(32, 16) // the size must be smaller than the parent's size
            .withPosition(1, 1)
            .build() // position is always relative to the parent


        val header = Components.header() // this will be 1x1 left and down from the top left
            // corner of the panel
            .withPosition(1, 1)
            .withText("Header")
            .build()

        val checkBox: CheckBox = Components.checkBox()
            .withText("Check me!")
            .withPosition(
                Position.create(0, 1) // the position class has some convenience methods
                    // for you to specify your component's position as
                    // relative to another one
                    .relativeToBottomOf(header)
            )
            .build()

        val left: Button = Components.button()
            .withPosition(
                Position.create(0, 1) // this means 1 row below the check box
                    .relativeToBottomOf(checkBox)
            )
            .withText("Left")
            .build()

        val right: Button = Components.button()
            .withPosition(
                Position.create(1, 0) // 1 column right relative to the left BUTTON
                    .relativeToRightOf(left)
            )
            .withText("Right")
            .build()

        panel.addComponent(header)
        panel.addComponent(checkBox)
        panel.addComponent(left)
        panel.addComponent(right)

        screen.addComponent(panel)
    }
}