package attributes

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.int
import org.hexworks.amethyst.api.base.BaseAttribute

data class PlayerRace(val jsonObject: JsonObject) : BaseAttribute() {
    val name: String = jsonObject["name"].toString().replace("\"", "")
    // TODO: Starting proficiencies
    // TODO: Languages
    // TODO: Traits
}