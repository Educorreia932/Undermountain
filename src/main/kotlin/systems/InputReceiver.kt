package systems

import entities.Player
import enums.GameState
import extensions.GameEntity
import extensions.position
import game.GameConfig
import game.GameContext
import game.MetaContext
import messages.CastSpell
import messages.InspectInventory
import messages.InspectSpells
import messages.MoveTo
import messages.PickItemUp
import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.cobalt.logging.api.LoggerFactory
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEvent

object InputReceiver : BaseBehavior<GameContext>() {
    private val logger = LoggerFactory.getLogger(this::class)

    override suspend fun update(entity: GameEntity<EntityType>, context: GameContext): Boolean {
        val (world, _, uiEvent, player, target) = context

        if (MetaContext.gameState == GameState.PLAYER_TURN) {
            val currentPos = player.position

            if (uiEvent is KeyboardEvent) {
                when (uiEvent.code) {
                    KeyCode.UP -> player.moveTo(currentPos.withRelativeY(-1), context)
                    KeyCode.LEFT -> player.moveTo(currentPos.withRelativeX(-1), context)
                    KeyCode.DOWN -> player.moveTo(currentPos.withRelativeY(1), context)
                    KeyCode.RIGHT -> player.moveTo(currentPos.withRelativeX(1), context)
                    KeyCode.KEY_G -> player.pickItemUp(currentPos, context)
                    KeyCode.KEY_I -> player.inspectInventory(currentPos, context)
                    KeyCode.KEY_Z -> player.inspectSpells(currentPos, context)

                    else -> {
                        logger.debug("UI Event ($uiEvent) does not have a corresponding command, it is ignored.")
                    }
                }
            }
        }
        else if (MetaContext.gameState == GameState.TARGETING) {
            val currentPos = target.position

            if (uiEvent is KeyboardEvent) {
                when (uiEvent.code) {
                    KeyCode.UP -> target.moveTo(currentPos.withRelativeY(-1), context)
                    KeyCode.LEFT -> target.moveTo(currentPos.withRelativeX(-1), context)
                    KeyCode.DOWN -> target.moveTo(currentPos.withRelativeY(1), context)
                    KeyCode.RIGHT -> target.moveTo(currentPos.withRelativeX(1), context)

                    KeyCode.ENTER -> {
                        println(":OOOOO")

                        val selectedTarget = world.fetchBlockAtOrNull(currentPos)!!.occupier.get()

                        MetaContext.suspendedAction?.let { 
                            it as CastSpell
                            it.target = selectedTarget
                            selectedTarget.receiveMessage(it)
                        }
                    }

                    KeyCode.ESCAPE -> {
                        MetaContext.gameState = GameState.PLAYER_TURN
                        MetaContext.suspendedAction = null
                    }

                    else -> logger.debug("UI Event ($uiEvent) does not have a corresponding command, it is ignored.")
                }
            }
        }

        return true
    }

    private suspend fun GameEntity<EntityType>.moveTo(position: Position3D, context: GameContext) {
        receiveMessage(MoveTo(context, this, position))
    }

    private suspend fun GameEntity<Player>.pickItemUp(position: Position3D, context: GameContext) {
        receiveMessage(PickItemUp(context, this, position))
    }

    private suspend fun GameEntity<Player>.inspectInventory(position: Position3D, context: GameContext) {
        receiveMessage(InspectInventory(context, this, position))
    }

    private suspend fun GameEntity<Player>.inspectSpells(position: Position3D, context: GameContext) {
        receiveMessage(InspectSpells(context, this, position))
    }
}
