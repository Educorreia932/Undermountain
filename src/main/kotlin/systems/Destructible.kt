package systems

import functions.logGameEvent
import game.GameContext
import messages.Destroy
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet

object Destructible : BaseFacet<GameContext, Destroy>(Destroy::class) {
    override suspend fun receive(message: Destroy): Response {
        val (context, _, target) = message
        
        context.world.removeEntity(target)

        logGameEvent("$target dies.", Destructible)
        
        return Consumed
    }
}