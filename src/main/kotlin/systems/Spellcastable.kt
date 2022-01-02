package systems

import entities.effects
import enums.GameState
import game.GameContext
import game.MetaContext
import messages.CastSpell
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet

object Spellcastable: BaseFacet<GameContext, CastSpell>(CastSpell::class) {
    override suspend fun receive(message: CastSpell): Response {
        val (context, source, target, spell) = message
        
        spell.effects.forEach {
            it(context, source, target)
        }

        MetaContext.gameState = GameState.PLAYER_TURN
        MetaContext.suspendedAction = null
        
        return Consumed
    }
}