package utils

import java.text.SimpleDateFormat
import java.util.*

/** @author Nabi Ahmad
 * [github.com/csnabiahmad]
 * Mon, 29 May, 2023
 */

fun getCurrentDate(pattern: String = DATE_FORMAT): String =
    SimpleDateFormat(pattern, Locale.getDefault()).format(Date())

fun getCurrentTimestamp(): Long {
    return ((Calendar.getInstance().time).time / 1000)
}