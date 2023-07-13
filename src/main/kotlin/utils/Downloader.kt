/*
* Copyright 2023 Taleemabad (https://www.taleemabad.com/)
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     https://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package utils

import com.kadirkuruca.newsapp.extensions.fileName
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.delay
import java.io.File
import java.net.URLDecoder

/** @author Nabi Ahmad
 * [github.com/csnabiahmad]
 * Mon, 29 May, 2023
 */

class Downloader {
    suspend fun downloadVideo(client: HttpClient, url: String): String? {
        val fileName = URLDecoder.decode(url.fileName())
        val file = File(downloadDirectory, fileName)
        if (file.exists()) {
            println("File already Downloaded => Moving to Compression...")
            return fileName
        }
        runCatching {
            val directory = File(downloadDirectory)
            if (!directory.exists()) {
                directory.mkdirs() // Create the directory.
                println("Directory created: $downloadDirectory")
            }
            println("Download started: ${getCurrentDate()}")
            val response: HttpResponse = client.get(url)
            val content: ByteArray = response.body<ByteArray>()
            file.writeBytes(content)
            println("File $fileName saved to directory: $downloadDirectory")
            println("Download ended: ${getCurrentDate()}")
        }.onFailure {
            println("Failed to download: $fileName")
            println("Failed at: ${getCurrentDate()}")
            println("Cause: ${it.message}")
            client.close()
            return null
        }
        delay(3000)
        return fileName
    }
}