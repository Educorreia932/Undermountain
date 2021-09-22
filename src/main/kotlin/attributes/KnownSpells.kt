package attributes

import entities.Spell
import extensions.GameEntity
import org.hexworks.amethyst.api.base.BaseAttribute

class KnownSpells(
    var currentSpells: List<GameEntity<Spell>>
) : BaseAttribute() {

    val size: Int
        get() = currentSpells.count()
}