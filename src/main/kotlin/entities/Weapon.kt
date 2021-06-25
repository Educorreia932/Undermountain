package entities

import attributes.WeaponStats
import extensions.GameEntity
import org.hexworks.amethyst.api.base.BaseEntityType
import utils.DiceRoll

interface Weapon : CombatItem

val GameEntity<Weapon>.attackValue: DiceRoll
    get() = findAttribute(WeaponStats::class).get().damage

object Sword : BaseEntityType(
    name = "Sword",
), Weapon

object Scimitar : BaseEntityType(
    name = "Scimitar" 
), Weapon