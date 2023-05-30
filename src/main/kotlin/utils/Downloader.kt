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

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import java.io.File

/** @author Nabi Ahmad
 * [github.com/csnabiahmad]
 * Mon, 29 May, 2023
 */

class Downloader {
    suspend fun downloadVideo(client: HttpClient, url: String) {
        val fileName = url.substringAfterLast("/").replace("+"," ")
        val file = File(downloadDirectory, fileName)
        runCatching {
            val directory = File(downloadDirectory)
            if (!directory.exists()) {
                // Create the directory.
                directory.mkdirs()
                println("Directory created: $downloadDirectory")
            }
            println("Download started: ${getCurrentDate()}")
            val response: HttpResponse = client.get(url)
            val content: ByteArray = response.body<ByteArray>()
            file.writeBytes(content)
            println("File $fileName saved to directory: $downloadDirectory")
            println("Download ended: ${getCurrentDate()}")
            Coroutines.executeCoroutineIO {
                Compressor().compressVideo(
                    fileName
                )
            }
        }.onFailure {
            println("Failed to download: $fileName")
            println("Failed at: ${getCurrentDate()}")
            client.close()
        }
    }
}