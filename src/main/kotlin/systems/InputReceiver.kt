package systems

import builders.EntityFactory.newFirebolt
import entities.Goblin
import entities.Player
import extensions.AnyGameEntity
import extensions.GameEntity
import extensions.position
import extensions.tile
import game.GameContext
import messages.CastSpell
import messages.InspectInventory
import messages.MoveTo
import messages.PickItemUp
import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.modifier.SimpleModifiers
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEvent

object InputReceiver : BaseBehavior<GameContext>() {
    var selectingTarget = false
    lateinit var selectedTarget: AnyGameEntity

    override suspend fun update(entity: Entity<EntityType, GameContext>, context: GameContext): Boolean {
        val (world, _, uiEvent, player) = context
        val currentPos = player.position

        if (selectingTarget) {
            if (uiEvent is KeyboardEvent) {
                when (uiEvent.code) {
                    KeyCode.ESCAPE -> selectingTarget = false
                    KeyCode.ENTER -> player.selectTarget(context, selectedTarget)

                    else -> {
                        // logger.debug("UI Event ($uiEvent) does not have a corresponding command, it is ignored.")
                    }
                }
            }
        }
        
        else {
            if (uiEvent is KeyboardEvent) {
                when (uiEvent.code) {
                    KeyCode.UP -> player.moveTo(currentPos.withRelativeY(-1), context)
                    KeyCode.LEFT -> player.moveTo(currentPos.withRelativeX(-1), context)
                    KeyCode.DOWN -> player.moveTo(currentPos.withRelativeY(1), context)
                    KeyCode.RIGHT -> player.moveTo(currentPos.withRelativeX(1), context)
                    KeyCode.KEY_G -> player.pickItemUp(currentPos, context)
                    KeyCode.KEY_I -> player.inspectInventory(currentPos, context)
                    KeyCode.KEY_Z -> updateSelectedTarget(context)

                    else -> {
                        // logger.debug("UI Event ($uiEvent) does not have a corresponding command, it is ignored.")
                    }
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
    
    private suspend fun updateSelectedTarget(context: GameContext) {
        val (world, _, _, player) = context

        selectingTarget = true

        selectedTarget = world.fetchEntitiesOfType<Goblin>().last()
        selectedTarget.tile = selectedTarget.tile.withAddedModifiers(SimpleModifiers.Blink)
        
        // Dumb fix for the modifier to work
        selectedTarget.receiveMessage(MoveTo(context, selectedTarget , selectedTarget.position.withRelativeX(1)))
        selectedTarget.receiveMessage(MoveTo(context, selectedTarget , selectedTarget.position.withRelativeX(-1)))

    }
}
