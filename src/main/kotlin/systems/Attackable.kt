package systems

import entities.*
import extensions.isPlayer
import functions.logGameEvent
import game.GameContext
import messages.Attack
import messages.Destroy
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet
import utils.DiceRoll

object Attackable : BaseFacet<GameContext, Attack>(Attack::class) {
    override suspend fun receive(message: Attack): Response {
        val (context, attacker, target) = message

        return if (attacker.isPlayer || target.isPlayer) {
            val attackRoll = DiceRoll(1, 20).roll() + 2 + attacker.creatureAttributes.strength.getModifier()
            println(attackRoll)

            if (attackRoll < target.combatStats.ac)
                logGameEvent("The $attacker misses!", Attackable)
            
            else {
                val damage = attacker.equippedWeapon.attackValue.roll()

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
            }

            Consumed
        } else Pass
    }
}