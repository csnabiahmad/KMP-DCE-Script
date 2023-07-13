package utils

import kotlinx.coroutines.delay
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.charset.StandardCharsets
import javax.crypto.Cipher
import javax.crypto.CipherOutputStream
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


class Encryptor {
    suspend fun encrypt(fileName: String) {
        runCatching {
            val file = File(encryptedDirectory, fileName.replace(".mp4", ".enc"))
            if (file.exists()) {
                println("File already Encrypted!")
                return
            }
            println("Encryption started at: ${getCurrentDate()} of $fileName")
            // Here you read the cleartext
            val fis = FileInputStream(compressDirectory.plus(fileName))
            // This stream write the encrypted text. This stream will be wrapped by another stream
            val fos = FileOutputStream(encryptedDirectory.plus(fileName.replace(".mp4", ".enc")))
            val initializationVector = "1234567890123456"
            val ivBytes: ByteArray = initializationVector.toByteArray(StandardCharsets.UTF_8)
            val ivSpec = IvParameterSpec(ivBytes)
            // Length is 16 byte
            val sks = SecretKeySpec("MyDifficultPassw".toByteArray(), "AES")
            // Create cipher
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, sks, ivSpec)
            // Wrap the output stream
            val cos = CipherOutputStream(fos, cipher)
            // Create Enc file
            FileIO().byteArrayToEnc(fis,cos,fileName)
            delay(2000)
        }.onFailure {
            println("Failed to encrypt: $fileName")
            println("Cause: ${it.message}")
        }

    }
}