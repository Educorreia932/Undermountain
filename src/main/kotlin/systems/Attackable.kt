package systems

import entities.combatStats
import extensions.hasNoHealthLeft
import extensions.isPlayer
import functions.logGameEvent
import game.GameContext
import messages.Attack
import messages.Destroy
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet

object Attackable: BaseFacet<GameContext, Attack>(Attack::class) {
    override suspend fun receive(message: Attack): Response {
        val (context, attacker, target) = message
        
        return if (attacker.isPlayer || target.isPlayer) {
            val damage = 5
            
            target.combatStats.currentHp -= damage

            logGameEvent("The $attacker hits the $target for $damage!", Attackable)
            
            if (target.hasNoHealthLeft()) {
                target.sendMessage(
                    Destroy(
                        context = context,
                        source = attacker,
                        target = target
                    )
                )
            }
            
            Consumed
        } else Pass
    }
}