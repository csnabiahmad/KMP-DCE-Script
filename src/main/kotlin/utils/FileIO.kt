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

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import javax.crypto.CipherOutputStream

/** @author Nabi Ahmad
 * [github.com/csnabiahmad]
 * Mon, 29 May, 2023
 */

class FileIO {
    fun createBundleDirectories(){
        val downloadDir = File(downloadDirectory)
        val compressDir = File(compressDirectory)
        val encryptDir = File(encryptedDirectory)
        runCatching {
            if (!downloadDir.exists()){
                downloadDir.mkdirs()
            }
            if (!compressDir.exists()){
            compressDir.mkdirs()
            }
            if (!encryptDir.exists()){
            encryptDir.mkdirs()
            }
        }.onFailure {
            println(it.message)
        }

    }
    fun fetchFileBytes(filePath: String): ByteArray? {
        return try {
            val file = File(filePath)
            if (!file.exists() || file.isDirectory) {
                throw IOException("File does not exist or is a directory")
            }
            file.readBytes()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }

    fun byteArrayToEnc(fis:FileInputStream , cos: CipherOutputStream , fileName: String){
        val directory = File(encryptedDirectory)
        if (!directory.exists()) {
            // Create the directory.
            directory.mkdirs()
            println("Directory created: $encryptedDirectory")
        }
        var length: Int
        val bytes = ByteArray(1024 * 1024)
        while (fis.read(bytes).also { length = it } != -1) {
            cos.write(bytes, 0, length)
        }
        println("Encryption ended at: ${getCurrentDate()} of ${fileName.replace(".mp4", ".enc")}")
        cos.flush()
        cos.close()
        fis.close()

    }

    fun byteArrayToMP4(byteArray: ByteArray, fileName: String) {
        val directory = File(compressDirectory)
        if (!directory.exists()) {
            // Create the directory.
            directory.mkdirs()
            println("Directory created: $compressDirectory")
        }
        val fileOutputStream = FileOutputStream(compressDirectory.plus(fileName))
        fileOutputStream.write(byteArray)
        fileOutputStream.close()
        println("File $fileName saved to directory: $compressDirectory")
    }

}