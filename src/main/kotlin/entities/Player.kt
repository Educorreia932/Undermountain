package entities

import attributes.CreatureAttributes
import attributes.Equipment
import attributes.Stats
import extensions.GameEntity
import org.hexworks.amethyst.api.base.BaseEntityType
import org.hexworks.amethyst.api.entity.EntityType

interface Combatant : EntityType

val GameEntity<Combatant>.combatStats: Stats
    get() = findAttribute(Stats::class).get()

val GameEntity<Combatant>.equippedWeapon: GameEntity<Weapon>
    get() = findAttribute(Equipment::class).get().weapon

val GameEntity<Combatant>.creatureAttributes: CreatureAttributes
    get() = findAttribute(CreatureAttributes::class).get()

open class Creature(name: String): BaseEntityType(
    name = name
), Combatant

object Player : Creature(
    name = "player"
), ExperienceGainer, ItemHolder, EquipmentHolder

object Goblin : Creature(
    name = "goblin"
)