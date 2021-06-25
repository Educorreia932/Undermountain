package builders

import attributes.*
import attributes.classes.Fighter
import attributes.flags.BlockOccupier
import attributes.races.Human
import entities.*
import game.GameContext
import game.GameTileRepository.GOBLIN
import game.GameTileRepository.PLAYER
import game.GameTileRepository.SWORD
import game.GameTileRepository.WALL
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

    fun newPlayer(): Entity<Player, GameContext> {
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

    fun newGoblin() = newGameEntityOfType(Goblin) {
        attributes(
            EntityPosition(),
            EntityTile(GOBLIN),
            CreatureAttributes.create(8, 14, 10, 10, 8, 8),
            Stats.create(
                maxHp = 7,
                ac = 15
            ),
            Equipment(initialWeapon = newScimitar())
        )
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
    
    private fun newScimitar() = newGameEntityOfType(Scimitar) {
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
    
    fun newWall() = newGameEntityOfType(Wall) {
        attributes(
            EntityPosition(),
            BlockOccupier,
            EntityTile(WALL)
        )
    }
}