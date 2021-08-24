package systems

import entities.effects
import game.GameContext
import messages.CastSpell
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet

object Spellcastable: BaseFacet<GameContext, CastSpell>(CastSpell::class) {
    override suspend fun receive(message: CastSpell): Response {
        println("Received spell")
        val (context, source, target, spell) = message
        
        spell.effects.forEach {
            it(context, source, target)
        }
        
        return Consumed
    }
}