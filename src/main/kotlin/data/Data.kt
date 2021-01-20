package data

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray

object Data {
    val data: Map<DataType, JsonArray> = load()

    private fun loadResource(file: String): String = javaClass.getResource(file).readText()

    fun load(): Map<DataType, JsonArray> {
        return mapOf(
            DataType.Race to loadRaces(),
            DataType.Class to loadClasses()
        )
    }

    private fun loadRaces(): JsonArray {
        val lines: String = loadResource("/5e-SRD-Races.json")

        return Json.parseToJsonElement(lines) as JsonArray
    }

    private fun loadClasses(): JsonArray {
        val lines: String = loadResource("/5e-SRD-Classes.json")

        return Json.parseToJsonElement(lines) as JsonArray
    }

    fun getDataObject(dataType: DataType): JsonArray {
        return data[dataType]!!
    }
}