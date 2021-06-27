package attributes.classes

import org.hexworks.amethyst.api.base.BaseAttribute

abstract class PlayerClass(
    name: String,
    description: String,
    hitDie: Int
): BaseAttribute()