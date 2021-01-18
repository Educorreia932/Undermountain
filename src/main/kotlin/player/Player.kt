package player

import creature.Creature
import player.classes.PlayerClass
import player.races.PlayerRace

class Player : Creature {
    var playerRace: PlayerRace = PlayerRace()
    var playerClass: PlayerClass = PlayerClass()

    // TODO: Saving Throws
    // TODO: Skills

    constructor() {
    }
}