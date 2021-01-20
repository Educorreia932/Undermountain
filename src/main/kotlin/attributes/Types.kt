package attributes

import extensions.GameEntity
import org.hexworks.amethyst.api.base.BaseEntityType
import org.hexworks.amethyst.api.entity.EntityType

interface Combatant : EntityType

val GameEntity<Combatant>.combatStats: Stats
    get() = findAttribute(Stats::class).get()

open class Creature(name: String): BaseEntityType(
    name = name
), Combatant

object Player : Creature(
    name = "player"
)