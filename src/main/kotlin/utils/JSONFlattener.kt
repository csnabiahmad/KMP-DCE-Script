package utils

import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.net.URI

object JSONFlattener {
    /**
     * The JSONObject type
     */
    private val JSON_OBJECT: Class<*> = JSONObject::class.java

    /**
     * The JSONArray type
     */
    private val JSON_ARRAY: Class<*> = JSONArray::class.java

    /**
     * Parse the JSON content at the given URI using the default
     * character encoding UTF-8
     *
     * @param uri
     * @return
     */
    fun parseJson(uri: URI?): List<Map<String, String>> {
        return parseJson(uri, "UTF-8")!!
    }

    /**
     * Parse the JSON content at the given URI using the specified
     * character encoding
     *
     * @param uri
     * @return
     */
    fun parseJson(uri: URI?, encoding: String): List<Map<String, String>>? {
        var flatJson: List<Map<String, String>>? = null
        var json = ""
        try {
            json = IOUtils.toString(uri, encoding)
            flatJson = parseJson(json)
        } catch (e: IOException) {
            println("JsonFlattener#ParseJson(uri, encoding) IOException: $e")
        } catch (ex: Exception) {
            println("JsonFlattener#ParseJson(uri, encoding) Exception: $ex")
        }
        return flatJson
    }
    /**
     * Parse the JSON file using the specified character encoding
     *
     * @param file
     * @return
     */
    /**
     * Parse the JSON file using the default character encoding UTF-8
     *
     * @param file
     * @return
     */
    @JvmOverloads
    fun parseJson(file: File?, encoding: String = "UTF-8"): List<Map<String, String>>? {
        var flatJson: List<Map<String, String>>? = null
        var json: String = ""
        try {
            json = FileUtils.readFileToString(file, encoding)
            flatJson = parseJson(json)
        } catch (e: IOException) {
            println("JsonFlattener#ParseJson(file, encoding) IOException: $e")
        } catch (ex: Exception) {
            println("JsonFlattener#ParseJson(file, encoding) Exception: $ex")
        }
        return flatJson
    }

    /**
     * Parse the JSON String
     *
     * @param json
     * @return
     * @throws Exception
     */
    fun parseJson(json: String): MutableList<Map<String, String>> {
        var flatJson: MutableList<Map<String, String>> = mutableListOf()
        try {
            val jsonObject = JSONObject(json)
            flatJson = ArrayList()
            flatJson.add(parse(jsonObject))
        } catch (je: JSONException) {
            println("Handle the JSON String as JSON Array")
            flatJson = handleAsArray(json)
        }
        return flatJson
    }

    /**
     * Parse a JSON Object
     *
     * @param jsonObject
     * @return
     */
    fun parse(jsonObject: JSONObject): Map<String, String> {
        val flatJson: MutableMap<String, String> = LinkedHashMap()
        flatten(jsonObject, flatJson, "")
        return flatJson
    }

    /**
     * Parse a JSON Array
     *
     * @param jsonArray
     * @return
     */
    fun parse(jsonArray: JSONArray): MutableList<Map<String, String>> {
        var jsonObject: JSONObject? = null
        val flatJson: MutableList<Map<String, String>> = mutableListOf()
        val length = jsonArray.length()
        for (i in 0 until length) {
            jsonObject = jsonArray.getJSONObject(i)
            val stringMap = parse(jsonObject)
            flatJson.add(stringMap)
        }
        return flatJson
    }

    /**
     * Handle the JSON String as Array
     *
     * @param json
     * @return
     * @throws Exception
     */
    private fun handleAsArray(json: String): MutableList<Map<String, String>> {
        var flatJson: MutableList<Map<String, String>> = mutableListOf()
        try {
            val jsonArray = JSONArray(json)
            flatJson = parse(jsonArray)
        } catch (e: Exception) {
            // throw new Exception("Json might be malformed");
            println("JSON might be malformed, Please verify that your JSON is valid")
        }
        return flatJson
    }

    /**
     * Flatten the given JSON Object
     *
     * This method will convert the JSON object to a Map of
     * String keys and values
     *
     * @param obj
     * @param flatJson
     * @param prefix
     */
    private fun flatten(obj: JSONObject?, flatJson: MutableMap<String, String>, prefix: String) {
        val iterator: Iterator<*> = obj!!.keys()
        val _prefix = if (prefix !== "") "$prefix." else ""
        while (iterator.hasNext()) {
            val key = iterator.next().toString()
            if (obj!![key].javaClass == JSON_OBJECT) {
                val jsonObject = obj!![key] as JSONObject
                flatten(jsonObject, flatJson, _prefix + key)
            } else if (obj!![key].javaClass == JSON_ARRAY) {
                val jsonArray = obj!![key] as JSONArray
                if (jsonArray.length() < 1) {
                    continue
                }
                flatten(jsonArray, flatJson, _prefix + key)
            } else {
                val value = obj!![key].toString()
                if (value != null && value != "null") {
                    flatJson[_prefix + key] = value
                }
            }
        }
    }

    /**
     * Flatten the given JSON Array
     *
     * @param obj
     * @param flatJson
     * @param prefix
     */
    private fun flatten(obj: JSONArray, flatJson: MutableMap<String, String>, prefix: String) {
        val length = obj.length()
        for (i in 0 until length) {
            if (obj[i].javaClass == JSON_ARRAY) {
                val jsonArray = obj[i] as JSONArray

                // jsonArray is empty
                if (jsonArray.length() < 1) {
                    continue
                }
                flatten(jsonArray, flatJson, "$prefix[$i]")
            } else if (obj[i].javaClass == JSON_OBJECT) {
                val jsonObject = obj[i] as JSONObject
                flatten(jsonObject, flatJson, prefix + "[" + (i + 1) + "]")
            } else {
                val value = obj[i].toString()
                if (value != null) {
                    flatJson[prefix + "[" + (i + 1) + "]"] = value
                }
            }
        }
    }
}
