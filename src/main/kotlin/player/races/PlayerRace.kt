package player.races

import creature.CreatureSize
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.int

class PlayerRace {
    val name: String
    val speed: Int
    // TODO: Ability bonuses
    val size: CreatureSize = CreatureSize.MEDIUM
    // TODO: Starting proficiencies
    // TODO: Languages
    // TODO: Traits

    constructor(jsonObject: JsonObject) {
        name = jsonObject["name"].toString().replace("\"", "")
        speed = (jsonObject["speed"] as JsonPrimitive).int
        println(jsonObject["size"])

        println(speed)
    }
}