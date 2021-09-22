package builders

import attributes.VisionBlocker
import attributes.*
import attributes.classes.PlayerClass
import attributes.flags.BlockOccupier
import attributes.races.PlayerRace
import entities.*
import entities.FOW
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
            Equipment(),
            playerClass,
            playerRace,
            abilities,
            Vision(9),
            KnownSpells(listOf(newAcidSplash(), newFirebolt()))
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

    fun newFirebolt() = newGameEntityOfType(Firebolt) {
        attributes(
            SpellStats(
                level = 0,
                school = MagicSchool.Evocation,
                components = setOf(SpellComponent.V, SpellComponent.S),
                range = 120,
                duration = 0,
                effects = listOf { _, caster, target ->
                    val finalDamage = DiceRoll(1, 10).roll()

                    target.whenTypeIs<Combatant> {
                        logGameEvent("The fire bolt burns the $it for $finalDamage damage!", caster)
                    }
                }
            )
        )
    }
    
    fun newAcidSplash() = newGameEntityOfType(AcidSplash) {
        attributes(
            SpellStats(
                level = 0,
                school = MagicSchool.Conjuration,
                components = setOf(SpellComponent.V, SpellComponent.S),
                range = 60,
                duration = 0,
                effects = listOf { _, caster, target ->
                    val finalDamage = DiceRoll(1, 6).roll()
                    
                    target.whenTypeIs<Combatant> {  
                        logGameEvent("The acid splash hurts the $it for $finalDamage damage!", caster)
                    }
                }
            )
        )
    }
    
    fun newFogOfWar() = newGameEntityOfType(FOW) {
        behaviors(FogOfWar)
    }
}
