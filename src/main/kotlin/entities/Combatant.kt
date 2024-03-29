package entities

import attributes.Abilities
import attributes.Stats
import extensions.GameEntity
import org.hexworks.amethyst.api.entity.EntityType

interface Combatant : EntityType

val GameEntity<Combatant>.combatStats: Stats
    get() = findAttribute(attributes.Stats::class).get()

val GameEntity<Combatant>.equippedWeapon: GameEntity<Weapon>
    get() = findAttribute(attributes.Equipment::class).get().weapon

val GameEntity<Combatant>.abilities: Abilities
    get() = findAttribute(attributes.Abilities::class).get()

fun GameEntity<Combatant>.hasNoHealthLeft(): Boolean = combatStats.currentHp <= 0