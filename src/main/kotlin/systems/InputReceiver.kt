package systems

import builders.EntityFactory.newFirebolt
import entities.Player
import extensions.GameEntity
import extensions.position
import game.GameConfig
import game.GameContext
import messages.CastSpell
import messages.InspectInventory
import messages.MoveTo
import messages.PickItemUp
import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cobalt.logging.api.LoggerFactory
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEvent
import org.hexworks.zircon.api.uievent.MouseEvent
import org.hexworks.zircon.api.uievent.MouseEventType

object InputReceiver : BaseBehavior<GameContext>() {
    private var selectingTarget = false // TODO: Use State pattern, instead of just a boolean
    private val logger = LoggerFactory.getLogger(this::class)

    override suspend fun update(entity: GameEntity<EntityType>, context: GameContext): Boolean {
        val (world, _, uiEvent, player) = context
        val currentPos = player.position

        if (selectingTarget) {
            if (uiEvent is MouseEvent && uiEvent.type == MouseEventType.MOUSE_CLICKED) {
                val position = Position3D.create(
                    uiEvent.position.x - GameConfig.SIDEBAR_WIDTH,
                    uiEvent.position.y,
                    currentPos.z
                )
                val selectedTarget = world.fetchBlockAt(position).get().occupier.get()

                player.selectTarget(context, selectedTarget)
            }
            
            else if (uiEvent is KeyboardEvent) {
                when (uiEvent.code) {
                    KeyCode.ESCAPE -> selectingTarget = false

                    else -> logger.debug("UI Event ($uiEvent) does not have a corresponding command, it is ignored.")
                }
            }
        }
        
        else if (uiEvent is KeyboardEvent) {
            when (uiEvent.code) {
                KeyCode.UP -> player.moveTo(currentPos.withRelativeY(-1), context)
                KeyCode.LEFT -> player.moveTo(currentPos.withRelativeX(-1), context)
                KeyCode.DOWN -> player.moveTo(currentPos.withRelativeY(1), context)
                KeyCode.RIGHT -> player.moveTo(currentPos.withRelativeX(1), context)
                KeyCode.KEY_G -> player.pickItemUp(currentPos, context)
                KeyCode.KEY_I -> player.inspectInventory(currentPos, context)
                KeyCode.KEY_Z -> selectingTarget = true

                else -> {
                    logger.debug("UI Event ($uiEvent) does not have a corresponding command, it is ignored.")
                }
            }
        }

        return true
    }

    private suspend fun GameEntity<Player>.moveTo(position: Position3D, context: GameContext) {
        receiveMessage(MoveTo(context, this, position))
    }

    private suspend fun GameEntity<Player>.pickItemUp(position: Position3D, context: GameContext) {
        receiveMessage(PickItemUp(context, this, position))
    }

    private suspend fun GameEntity<Player>.inspectInventory(position: Position3D, context: GameContext) {
        receiveMessage(InspectInventory(context, this, position))
    }

    private suspend fun GameEntity<Player>.selectTarget(context: GameContext, target: Entity<EntityType, GameContext>) {
        receiveMessage(CastSpell(context, this, target, newFirebolt()))
    }
}
