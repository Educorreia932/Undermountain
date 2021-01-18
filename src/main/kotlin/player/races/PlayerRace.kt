package player.races

import creature.CreatureSize
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.int

class PlayerRace(jsonObject: JsonObject) {
    val name: String
    val speed: Int
    // TODO: Ability bonuses
    val size: CreatureSize
    // TODO: Starting proficiencies
    // TODO: Languages
    // TODO: Traits

    init {
        name = jsonObject["name"].toString().replace("\"", "")
        speed = (jsonObject["speed"] as JsonPrimitive).int
        size = CreatureSize.valueOf(jsonObject["size"].toString().replace("\"", ""))
    }
}