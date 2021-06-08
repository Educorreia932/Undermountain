package builders

import attributes.*
import attributes.types.Monster
import attributes.types.Player
import data.Data
import game.GameContext
import game.GameTileRepository.MONSTER
import game.GameTileRepository.PLAYER
import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.amethyst.api.newEntityOfType
import systems.CameraMover
import systems.InputReceiver
import systems.InventoryInspector
import systems.Movable

object EntityFactory {
    private fun <T : EntityType> newGameEntityOfType(
        type: T,
        init: EntityBuilder<T, GameContext>.() -> Unit
    ) = newEntityOfType(type, init)

    fun newPlayer(raceIndex: Int, classIndex: Int): Entity<Player, GameContext> {
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
                PlayerRace(Data.getRace(raceIndex)),
                PlayerClass(Data.getClass(classIndex)),
                Inventory()
            )
            facets(Movable, CameraMover, InventoryInspector)
            behaviors(InputReceiver)
        }
    }

    fun newMonster() = newGameEntityOfType(Monster) {
        attributes(
            EntityPosition(),
            EntityTile(MONSTER),
            Stats.create(
                maxHp = 5,
                ac = 8
            )
        )
        facets(Movable)
        behaviors()
    }
}