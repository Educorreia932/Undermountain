package builders

import attributes.*
import attributes.classes.PlayerClass
import attributes.flags.BlockOccupier
import attributes.races.PlayerRace
import entities.*
import enums.DamageType
import enums.MagicSchool
import enums.SpellComponent
import extensions.whenTypeIs
import functions.logGameEvent
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
    private fun <T : EntityType> newGameEntityOfType(
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
            Equipment(initialWeapon = newScimitar()),
            playerClass,
            playerRace,
            abilities
        )
        facets(Movable, CameraMover, InventoryInspector, ItemPicker, Spellcastable)
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

    fun newWall() = newGameEntityOfType(Wall) {
        attributes(
            EntityPosition(),
            BlockOccupier,
            EntityTile(WALL)
        )
    }

    fun newFirebolt() = newGameEntityOfType(Firebolt) {
        attributes(
            SpellStats(
                level = 0,
                school = MagicSchool.Evocation,
                components = setOf(SpellComponent.V, SpellComponent.S),
                range = 1,
                duration = 0,
                effects = listOf { context, caster, target ->
                    val finalDamage = DiceRoll(1, 10).roll()

                    target.whenTypeIs<Combatant> {
                        logGameEvent("The firebolt burns the $it for $finalDamage damage!", caster)
                    }
                }
            )
        )
    }
}
