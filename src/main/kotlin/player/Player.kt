package player

import creature.Creature
import kotlinx.serialization.json.JsonObject
import player.classes.PlayerClass
import player.races.PlayerRace

class Player : Creature {
    var playerRace: PlayerRace
    var playerClass: PlayerClass

    // TODO: Saving Throws
    // TODO: Skills

    constructor(
        raceObject: JsonObject,
        classObject: JsonObject
    ) : super() {
        playerRace = PlayerRace(raceObject)
        playerClass = PlayerClass(classObject)
    }
}