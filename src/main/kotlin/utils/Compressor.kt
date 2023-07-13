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

import io.github.techgnious.IVCompressor
import io.github.techgnious.dto.ResizeResolution
import io.github.techgnious.dto.VideoFormats
import kotlinx.coroutines.delay
import java.io.File

/** @author Nabi Ahmad
 * [github.com/csnabiahmad]
 * Mon, 29 May, 2023
 */

class Compressor {
    private var compressor = IVCompressor()
    private var fileIO = FileIO()
    suspend fun compressVideo(fileName: String) : String? {
        val file = File(compressDirectory.plus(fileName))
        if (file.exists()){
            println("File already Compressed => Moving to Encryption...")
            return fileName
        }
        fileIO.fetchFileBytes(downloadDirectory.plus(fileName))?.let { bytesArray ->
            runCatching {
                println("Compression starts at: ${getCurrentDate()} of ${compressDirectory}")
                val compress = compressor.reduceVideoSize(bytesArray, VideoFormats.MP4, ResizeResolution.R480P)
                fileIO.byteArrayToMP4(compress, fileName)
                println("Compression ended at: ${getCurrentDate()} of ${fileName}")
            }.onFailure {
                println("Failed to compress: $fileName")
                println("Cause: ${it.message}")
                return null
            }
        } ?: run {
            println("Path not found for file: $fileName")
            return null
        }
        delay(2000)
        return fileName
    }
}