package builders

import attributes.*
import attributes.flags.BlockOccupier
import entities.Goblin
import entities.Player
import entities.Scimitar
import entities.Wall
import game.GameContext
import game.GameTileRepository.GOBLIN
import game.GameTileRepository.PLAYER
import game.GameTileRepository.SWORD
import game.GameTileRepository.WALL
import messages.Attack
import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.Entity
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.amethyst.api.newEntityOfType
import org.hexworks.zircon.api.GraphicalTilesetResources
import org.hexworks.zircon.api.data.Tile
import systems.*
import utils.DiceRoll

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
                EntityActions(Attack::class),
                CreatureAttributes(),
                Stats.create(
                    maxHp = 10,
                    ac = 10
                ),
                Experience(),
                Inventory(),
                Equipment(initialWeapon = newScimitar()),
            )
            facets(Movable, CameraMover, InventoryInspector, ItemPicker)
            behaviors(InputReceiver)
        }
    }

    fun newGoblin() = newGameEntityOfType(Goblin) {
        attributes(
            EntityPosition(),
            EntityTile(GOBLIN),
            BlockOccupier,
            CreatureAttributes.create(8, 14, 10, 10, 8, 8),
            Stats.create(
                maxHp = 7,
                ac = 15
            ),
            Equipment(initialWeapon = newScimitar())
        )
        facets(Attackable, Destructible)
    }

    fun newScimitar() = newGameEntityOfType(Scimitar) {
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
                damage = DiceRoll(1, 6),
                damageType = DamageType.Slashing
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