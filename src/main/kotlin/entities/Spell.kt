package entities

import org.hexworks.amethyst.api.base.BaseEntityType
import org.hexworks.amethyst.api.entity.EntityType

interface Spell : EntityType

object Firebolt : BaseEntityType(
    name = "Firebolt",
    description = "Your hurl a mote of fire at a creature or object within range."
), Spell