package builders

import attributes.*
import data.Data
import data.DataType
import game.GameContext
import game.GameTileRepository.PLAYER
import kotlinx.serialization.json.JsonObject
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
    fun newPlayer() = newGameEntityOfType(Player) {
        attributes(
            EntityPosition(),
            EntityTile(PLAYER),
            CombatStats.create(
                maxHp = 10,
                ac = 10
            ),
            PlayerRace(Data.getDataObject(DataType.Race)[0] as JsonObject),
            PlayerClass(Data.getDataObject(DataType.Class)[0] as JsonObject)
        )
        behaviors(InputReceiver)
        facets(Movable, CameraMover)
    }
}