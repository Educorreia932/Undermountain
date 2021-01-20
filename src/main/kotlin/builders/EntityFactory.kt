package builders

import attributes.*
import data.Data
import game.GameContext
import game.GameTileRepository.PLAYER
import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.Entity
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
    fun newPlayer(raceIndex: Int, classIndex: Int): Entity<Player, GameContext> {
        val playerRace: PlayerRace = PlayerRace(Data.getRace(raceIndex))
        val playerClass: PlayerClass= PlayerClass(Data.getClass(raceIndex))

        return newGameEntityOfType(Player) {
            attributes(
                EntityPosition(),
                EntityTile(PLAYER),
                Attributes(),
                Stats.create(
                    maxHp = 10,
                    ac = 10
                ),
                playerRace,
                playerClass
            )
            behaviors(InputReceiver)
            facets(Movable, CameraMover)
        }
    }
}