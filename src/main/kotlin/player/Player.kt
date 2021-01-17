package player

import creature.Creature
import player.classes.PlayerClass
import player.races.PlayerRace

class Player : Creature {
    var playerRace: PlayerRace = PlayerRace()
    var playerClass: PlayerClass = PlayerClass()

    private var experiencePoints: Int = 0

    val level: Int
        get() = when (experiencePoints) {
            in 0..299 -> 1
            in 300..899 -> 2
            in 900..2699 -> 3
            in 2700..6499 -> 4
            in 6500..13999 -> 5
            in 14000..22999 -> 6
            in 23000..23999 -> 7
            in 34000..47999 -> 8
            in 48000..63999 -> 9
            in 64000..84999 -> 10
            in 85000..99999 -> 11
            in 100000..119999 -> 12
            in 120000..139999 -> 13
            in 140000..164999 -> 14
            in 165000..194999 -> 15
            in 195000..224999 -> 16
            in 225000..264999 -> 17
            in 265000..304999 -> 18
            in 305000..354999 -> 19
            else -> 20
        }

    var attributes: Map<Attribute, Int> = mapOf(
        Attribute.STRENGTH to 10,
        Attribute.DEXTERITY to 10,
        Attribute.CONSTITUTION to 10,
        Attribute.WISDOM to 10,
        Attribute.INTELLIGENCE to 10,
        Attribute.CHARISMA to 10,
    )

    // TODO: Saving Throws
    // TODO: Skills

    constructor() {
    }
}