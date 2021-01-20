package attributes

import creature.CreatureSize
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.int
import org.hexworks.amethyst.api.base.BaseAttribute

data class PlayerRace(val jsonObject: JsonObject) : BaseAttribute() {
    val name: String = jsonObject["name"].toString().replace("\"", "")
    val speed: Int = (jsonObject["speed"] as JsonPrimitive).int
    // TODO: Ability bonuses
    val size: CreatureSize = CreatureSize.valueOf(jsonObject["size"].toString().replace("\"", ""))
    // TODO: Starting proficiencies
    // TODO: Languages
    // TODO: Traits
}