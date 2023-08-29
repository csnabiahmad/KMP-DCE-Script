package utils

import org.apache.commons.io.FileUtils
import org.apache.commons.lang3.StringUtils
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.util.*


object CSVWriter {

    /**
     * Convert the given List of String keys-values as a CSV String.
     *
     * @param flatJson   The List of key-value pairs generated from the JSON String
     *
     * @return The generated CSV string
     */
    fun getCSV(flatJson: MutableList<Map<String, String>>): String {
        // Use the default separator
        return getCSV(flatJson, ",")
    }

    /**
     * Convert the given MutableList of String keys-values as a CSV String.
     *
     * @param flatJson   The MutableList of key-value pairs generated from the JSON String
     * @param separator  The separator can be: ',', ';' or '\t'
     *
     * @return The generated CSV string
     */
    fun getCSV(flatJson: MutableList<Map<String, String>>, separator: String): String {
        val headers = collectHeaders(flatJson)
        var csvString: String = StringUtils.join(headers.toTypedArray(), separator) + "\n"
        for (map in flatJson) {
            csvString = csvString + getSeperatedColumns(headers, map, separator) + "\n"
        }
        return csvString
    }

    /**
     * Write the given CSV string to the given file.
     *
     * @param csvString  The csv string to write into the file
     * @param fileName   The file to write (included the path)
     */
    fun writeToFile(csvString: String, fileName: String) {
        try {
            FileUtils.write(File(fileName), csvString)
        } catch (e: IOException) {
            println("CSVWriter#writeToFile(csvString, fileName) IOException: $e")
        }
    }

    /**
     * Write the given CSV from a flat json to the given file.
     *
     * @param flatJson
     * @param separator
     * @param fileName
     * @param headers
     */
    fun writeLargeFile(flatJson: MutableList<Map<String, String>>, separator: String, fileName: String, headers: Set<String>) {
        var csvString: String
        csvString = StringUtils.join(headers.toTypedArray(), separator) + "\n"
        val file = File(fileName)
        try {
            // ISO8859_1 char code to Latin alphabet
            FileUtils.write(file, csvString, "ISO8859_1")
            for (map in flatJson) {
                csvString = ""
                csvString = getSeperatedColumns(headers, map, separator) + "\n"
                Files.write(Paths.get(fileName), csvString.toByteArray(charset("ISO8859_1")), StandardOpenOption.APPEND)
            }
        } catch (e: IOException) {
            println("CSVWriter#writeLargeFile(flatJson, separator, fileName, headers) IOException: $e")
        }
    }

    /**
     * Get separated comlumns used a separator (comma, semi column, tab).
     *
     * @param headers The CSV headers
     * @param map     Map of key-value pairs contains the header and the value
     *
     * @return a string composed of columns separated by a specific separator.
     */
    private fun getSeperatedColumns(headers: Set<String>, map: Map<String, String>, separator: String): String {
        val items: MutableList<String> = ArrayList()
        for (header in headers) {
            val value = if (map[header] == null) "" else map[header]!!.replace("[\\,\\;\\r\\n\\t\\s]+".toRegex(), " ")
            items.add(value)
        }

        return StringUtils.join(items.toTypedArray(), separator)
    }

    /**
     * Get the CSV header.
     *
     * @param flatJson
     *
     * @return a Set of headers
     */
    private fun collectHeaders(flatJson: MutableList<Map<String, String>>): Set<String> {
        val headers: MutableSet<String> = LinkedHashSet()
        for (map in flatJson) {
            headers.addAll(map.keys)
        }
        return headers
    }

    /**
     * Get the CSV ordered header
     *
     * @param flatJson
     *
     * @return a Set of ordered headers
     */
    fun collectOrderedHeaders(flatJson: MutableList<Map<String, String>>): Set<String> {
        val headers: MutableSet<String> = TreeSet()
        for (map in flatJson) {
            headers.addAll(map.keys)
        }
        return headers
    }
}