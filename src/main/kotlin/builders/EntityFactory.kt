package builders

import attributes.EntityPosition
import attributes.EntityTile
import attributes.types.PlayerEntity
import game.GameContext
import game.GameTileRepository.PLAYER
import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.amethyst.api.newEntityOfType
import systems.CameraMover
import systems.InputReceiver
import systems.Movable

fun <T : EntityType> newGameEntityOfType(
    type: T,
    init: EntityBuilder<T, GameContext>.() -> Unit
) = newEntityOfType(type, init)

object EntityFactory {
    fun newPlayer() = newGameEntityOfType(PlayerEntity) {
        attributes(EntityPosition(), EntityTile(PLAYER))
        behaviors(InputReceiver)
        facets(Movable, CameraMover)
    }
}