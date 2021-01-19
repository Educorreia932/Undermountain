package systems

import game.GameContext
import messages.MoveTo
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet

object Movable : BaseFacet<GameContext, MoveTo>(MoveTo::class) { // 1
    override suspend fun receive(message: MoveTo): Response {
        val (context, entity, position) = message               // 2
        val world = context.world
        var result: Response = Pass                             // 3
        if (world.moveEntity(entity, position)) {               // 4
            result = Consumed                                   // 5
        }

        return result
    }
}