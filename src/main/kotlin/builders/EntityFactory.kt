package builders

import attributes.*
import attributes.types.Monster
import attributes.types.Player
import attributes.types.Sword
import data.Data
import game.GameContext
import game.GameTileRepository.ITEM
import game.GameTileRepository.MONSTER
import game.GameTileRepository.PLAYER
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
            EntityTile(ITEM),
            ItemIcon(
                Tile.newBuilder()
                    .withName("white gem")
                    .withTileset(GraphicalTilesetResources.nethack16x16())
                    .buildGraphicalTile()
            )
        )
        facets()
        behaviors()
    }
}