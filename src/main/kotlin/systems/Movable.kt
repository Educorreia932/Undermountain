package systems

import attributes.Player
import extensions.position
import game.GameContext
import messages.MoveCamera
import messages.MoveTo
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.MessageResponse
import org.hexworks.amethyst.api.Pass
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet

object Movable : BaseFacet<GameContext, MoveTo>(MoveTo::class) {
    override suspend fun receive(message: MoveTo): Response {
        val (context, entity, position) = message
        val world = context.world
        val previousPosition = entity.position
        var result: Response = Pass

        if (world.moveEntity(entity, position)) {
            result = if (entity.type == Player) {
                MessageResponse(
                    MoveCamera(
                        context = context,
                        source = entity,
                        previousPosition = previousPosition
                    )
                )
            } else Consumed
        }

        return result
    }
}