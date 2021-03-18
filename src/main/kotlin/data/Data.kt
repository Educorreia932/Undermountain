package data

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject

object Data {
    enum class DataType {
        Race,
        Class
    }

    val data: Map<DataType, JsonArray> = load()

    private fun loadResource(path: String): String = javaClass.getResource(path).readText()

    private fun load(): Map<DataType, JsonArray> {
        return mapOf(
            DataType.Race to loadRaces(),
            DataType.Class to loadClasses()
        )
    }

    private fun loadRaces(): JsonArray {
        val lines: String = loadResource("/data/races.json")

        return Json.parseToJsonElement(lines) as JsonArray
    }

    private fun loadClasses(): JsonArray {
        val lines: String = loadResource("/data/classes.json")

        return Json.parseToJsonElement(lines) as JsonArray
    }

    private fun getDataObject(dataType: DataType, index: Int): JsonObject {
        return data[dataType]!![index] as JsonObject
    }

    fun getRace(index: Int) = getDataObject(DataType.Race, index)

    fun getClass(index: Int) = getDataObject(DataType.Class, index)
}