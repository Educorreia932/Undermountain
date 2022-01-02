package entities

import org.hexworks.amethyst.api.base.BaseEntityType

interface Aberration
interface Beast
interface Celestial
interface Construct
interface Dragon
interface Elemental
interface Fey
interface Fiend
interface Giant
interface Humanoid
interface Monstrosity
interface Ooze
interface Plant
interface Undead

open class Creature(name: String): BaseEntityType(
    name = name
), Combatant

object Player : Creature(
    name = "Player"
), ExperienceGainer, ItemHolder, EquipmentHolder
    
object Goblin : Creature(
    name = "Goblin"
), Humanoid