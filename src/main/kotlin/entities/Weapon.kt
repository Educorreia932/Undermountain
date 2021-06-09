package entities

import attributes.WeaponStats
import extensions.GameEntity

interface Weapon : CombatItem

val GameEntity<Weapon>.attackValue: Int
    get() = findAttribute(WeaponStats::class).get().damage
