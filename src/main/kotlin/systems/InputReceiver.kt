package systems

import builders.EntityFactory.newFirebolt
import entities.Player
import entities.Spell
import extensions.GameEntity
import extensions.position
import game.GameContext
import messages.CastSpell
import messages.InspectInventory
import messages.MoveTo
import messages.PickItemUp
import org.hexworks.amethyst.api.base.BaseBehavior
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.zircon.api.data.Position3D
import org.hexworks.zircon.api.uievent.KeyCode
import org.hexworks.zircon.api.uievent.KeyboardEvent

object InputReceiver : BaseBehavior<GameContext>() {
    override suspend fun update(entity: Entity<EntityType, GameContext>, context: GameContext): Boolean {
        val (_, _, uiEvent, player) = context
        val currentPos = player.position

        if (uiEvent is KeyboardEvent) {
            when (uiEvent.code) {
                KeyCode.UP -> player.moveTo(currentPos.withRelativeY(-1), context)
                KeyCode.LEFT -> player.moveTo(currentPos.withRelativeX(-1), context)
                KeyCode.DOWN -> player.moveTo(currentPos.withRelativeY(1), context)
                KeyCode.RIGHT -> player.moveTo(currentPos.withRelativeX(1), context)
                KeyCode.KEY_G -> player.pickItemUp(currentPos, context)
                KeyCode.KEY_I -> player.inspectInventory(currentPos, context)
                KeyCode.KEY_Z -> player.selectTarget(context, newFirebolt())

                else -> {
                    // logger.debug("UI Event ($uiEvent) does not have a corresponding command, it is ignored.")
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

    private suspend fun GameEntity<Player>.selectTarget(context: GameContext, spell: Entity<Spell, GameContext>) {
        // DEBUG ONLY
        // This will cast a firebolt on entity at adjacent right position of player
        println("Spell cast")
        // val entity = context.world.fetchBlockAt(position.withRelativeX(1)).get().occupier.get()
        receiveMessage(CastSpell(context, this, this, spell))
    }
}
