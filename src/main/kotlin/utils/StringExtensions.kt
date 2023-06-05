/*
 *   Copyright 2020-2021 Leon Latsch
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.kadirkuruca.newsapp.extensions

/**
 * Returns an empty string.
 */
val String.Companion.empty: String
    get() = ""

/**
 * Remove a sequence from a string.
 */
fun String.remove(str: String): String = replace(str, String.empty)

fun String.isUri():Boolean{
    return startsWith("file:///data",true)
}

fun String.fileName():String {
    return this.substring(this.lastIndexOf("/")+1)
}

fun String.fileExtension():String {
    return this.substring(this.lastIndexOf(".")+1)
}

/**
 *urdu text check
 */
fun isUrdu(s: String): Boolean {
    var i = 0
    while (i < s.length) {
        val c = s.codePointAt(i)
        if (c in 0x0600..0x06E0) return true
        i += Character.charCount(c)
    }
    return false
}