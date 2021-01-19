package systems

import extensions.position
import game.GameContext
import messages.MoveCamera
import org.hexworks.amethyst.api.Consumed
import org.hexworks.amethyst.api.Response
import org.hexworks.amethyst.api.base.BaseFacet

object CameraMover : BaseFacet<GameContext, MoveCamera>(MoveCamera::class) {
    override suspend fun receive(message: MoveCamera): Response {
        val (context, source, previousPosition) = message
        val world = context.world
        val screenPos = source.position - world.visibleOffset
        val halfHeight = world.visibleSize.yLength / 2
        val halfWidth = world.visibleSize.xLength / 2
        val currentPosition = source.position

        when {
            previousPosition.y > currentPosition.y && screenPos.y < halfHeight -> {
                world.scrollOneBackward()
            }

            previousPosition.y < currentPosition.y && screenPos.y > halfHeight -> {
                world.scrollOneForward()
            }

            previousPosition.x > currentPosition.x && screenPos.x < halfWidth -> {
                world.scrollOneLeft()
            }

            previousPosition.x < currentPosition.x && screenPos.x > halfWidth -> {
                world.scrollOneRight()
            }
        }

        return Consumed
    }

}