package builders

import attributes.*
import attributes.types.Player
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

object EntityFactory {
    fun <T : EntityType> newGameEntityOfType(
        type: T,
        init: EntityBuilder<T, GameContext>.() -> Unit
    ) = newEntityOfType(type, init)

    fun newPlayer(raceIndex: Int, classIndex: Int): Entity<Player, GameContext> {
        val playerRace: PlayerRace = PlayerRace(Data.getRace(raceIndex))
        val playerClass: PlayerClass= PlayerClass(Data.getClass(classIndex))

        return newGameEntityOfType(Player) {
            attributes(
                EntityPosition(),
                EntityTile(PLAYER),
                Attributes(),
                Stats.create(
                    maxHp = 10,
                    ac = 10
                ),
                Experience(),
                playerRace,
                playerClass
            )
            behaviors(InputReceiver)
            facets(Movable, CameraMover)
        }
    }
}