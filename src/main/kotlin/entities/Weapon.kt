package entities

import attributes.WeaponStats
import extensions.GameEntity
import org.hexworks.amethyst.api.base.BaseEntityType

interface Weapon : CombatItem

val GameEntity<Weapon>.attackValue: Int
    get() = findAttribute(WeaponStats::class).get().damage

object Sword : BaseEntityType(
    name = "Sword",
), Weapon

object Scimitar : BaseEntityType(
    name = "Scimitar" 
), Weapon