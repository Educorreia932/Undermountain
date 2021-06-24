package builders

import attributes.*
import attributes.classes.Fighter
import attributes.races.Human
import entities.Monster
import entities.Player
import entities.Sword
import game.GameContext
import game.GameTileRepository.MONSTER
import game.GameTileRepository.PLAYER
import game.GameTileRepository.SWORD
import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.amethyst.api.newEntityOfType
import org.hexworks.zircon.api.GraphicalTilesetResources
import org.hexworks.zircon.api.data.Tile
import systems.*

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
                CreatureAttributes(),
                Stats.create(
                    maxHp = 10,
                    ac = 10
                ),
                Experience(),
                Fighter(),
                Human(),
                Inventory(),
                Equipment(initialWeapon = newSword())
            )
            facets(Movable, CameraMover, InventoryInspector, ItemPicker)
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

    fun newSword() = newGameEntityOfType(Sword) {
        attributes(
            EntityPosition(),
            EntityTile(SWORD),
            ItemIcon(
                Tile.newBuilder()
                    .withName("Short sword")
                    .withTileset(GraphicalTilesetResources.nethack16x16())
                    .buildGraphicalTile()
            ),
            WeaponStats(
                damage = 6
            )
        )
    }
}