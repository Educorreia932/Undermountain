package player.classes

import spells.Spell

open class PlayerClass {
    var hitDie: Int = 0
    var spells = mutableListOf<Spell>()

    // TODO: Saving Throw Proficiencies
    // TODO: Armor and Weapon Proficiencies
    // TODO: Features & Traits
}