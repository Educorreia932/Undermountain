package builders

import attributes.flags.VisionBlocker
import attributes.*
import attributes.classes.PlayerClass
import attributes.flags.BlockOccupier
import attributes.races.PlayerRace
import builders.SpellFactory.newAcidSplash
import builders.SpellFactory.newFirebolt
import builders.SpellFactory.newRayOfFrost
import entities.*
import enums.DamageType
import game.GameContext
import game.GameTileRepository.GOBLIN
import game.GameTileRepository.PLAYER
import game.GameTileRepository.SWORD
import game.GameTileRepository.WALL
import messages.Attack
import org.hexworks.amethyst.api.builder.EntityBuilder
import org.hexworks.amethyst.api.entity.EntityType
import org.hexworks.amethyst.api.newEntityOfType
import org.hexworks.zircon.api.GraphicalTilesetResources
import org.hexworks.zircon.api.data.Tile
import systems.*
import utils.DiceRoll

object EntityFactory {
    fun <T : EntityType> newGameEntityOfType(
        type: T,
        init: EntityBuilder<T, GameContext>.() -> Unit
    ) = newEntityOfType(type, init)

    fun newPlayer(
        playerClass: PlayerClass,
        playerRace: PlayerRace,
        abilities: Abilities
    ) = newGameEntityOfType(Player) {
        attributes(
            EntityPosition(),
            EntityTile(PLAYER),
            EntityActions(Attack::class),
            Experience(),
            Inventory(),
            Equipment(),
            playerClass,
            playerRace,
            abilities,
            Vision(9),
            KnownSpells(listOf(
                newAcidSplash(), 
                newFirebolt(),
                newRayOfFrost()
            ))
        )
        facets(Movable, CameraMover, InventoryInspector, ItemPicker, Spellcastable, Spellcaster)
        behaviors(InputReceiver)
    }.asMutableEntity()

    fun newGoblin() = newGameEntityOfType(Goblin) {
        attributes(
            EntityPosition(),
            EntityTile(GOBLIN),
            BlockOccupier,
            Abilities.create(8, 14, 10, 10, 8, 8),
            Stats.create(
                maxHp = 7,
                ac = 15
            ),
            Equipment(initialWeapon = newScimitar())
        )
        facets(Attackable, Destructible, Spellcastable, Movable)
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

    fun newShortsword() = newGameEntityOfType(Shortsword) {
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
                damageType = DamageType.Piercing
            )
        )
    }

    fun newWall() = newGameEntityOfType(Wall) {
        attributes(
            EntityPosition(),
            BlockOccupier,
            EntityTile(WALL),
            VisionBlocker
        )
    }

    fun newFogOfWar() = newGameEntityOfType(FOW) {
        behaviors(FogOfWar)
    }
}
