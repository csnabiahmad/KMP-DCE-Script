package utils

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import java.io.File
import java.nio.charset.StandardCharsets

class SloGenerator {
    fun generateSLOFile() {
        saveSloRawDataIntoDb()
    }

    private fun saveSloRawDataIntoDb() {
        var mainArray = JsonArray()
        var formattedArray = JsonArray()
        kotlin.runCatching {
            mainArray = JsonDecoder().readJsonFromAssets(sloStage, JsonArray::class.java)
        }.onSuccess {
            for (i in 0 until mainArray.size()) {
                val jsonObject = mainArray.get(i).asJsonObject
                if (!jsonObject.getAsJsonObject("data").isJsonNull) {
                    val dataObject = jsonObject.getAsJsonObject("data")
                    val keys = dataObject.keySet()
                    keys.forEach {
                        val key = it
                        val obj = dataObject.getAsJsonObject(key)
                        val sloArray = obj.getAsJsonArray("slos")

                        for (j in 0 until sloArray.size()) {
                            val jsonObjectSloRawData = sloArray.get(j).asJsonObject
                            val convertedJson = JsonObject()
                            convertedJson.addProperty("uuid", jsonObjectSloRawData.get("uuid").asString)
                            convertedJson.addProperty(
                                "title", (jsonObjectSloRawData.get("title").asString)
                                    .replace("،", "").replace(",", "")
                            )
                            convertedJson.addProperty("slo-id", jsonObjectSloRawData.get("slo-id").asString)
                            convertedJson.addProperty("sloChapterUUID", jsonObject.get("uuid").asString)
                            formattedArray.add(convertedJson)
                        }
                    }
                } else {
                    println("Data is null for index: $i")
                }
            }
            jsonArrayToCsv(formattedArray)
        }.onFailure {
            println("${it.printStackTrace()}")
        }
    }

    fun jsonArrayToCsv(jsonArray: JsonArray) {
        // creating json file
        File(sloDirectory, "slo_rawdata.json").writeText(jsonArray.toString(), StandardCharsets.UTF_8)

        try {
            val csv = StringBuilder()
            val keys = jsonArray.get(0).asJsonObject.keySet()

            csv.append(keys.joinToString(",")).appendLine()

            for (i in 0 until jsonArray.size()) {
                val jsonObject = jsonArray.get(i).asJsonObject
                val values = keys.map { key -> jsonObject.get(key) }
                csv.append(values.joinToString(",")).appendLine()
                println("$i : ${values.joinToString(",")}")
            }

            File(sloDirectory, "slo_rawdata.csv").writeText(csv.toString(), StandardCharsets.UTF_8)

        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }
}