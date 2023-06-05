import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import remote.KtorClient
import utils.*

/** @author Nabi Ahmad
 * [github.com/csnabiahmad]
 * Mon, 29 May, 2023
 */


fun main() = runBlocking<Unit> {
    println(" *** Video Download, Compression & Encryption Script *** ")
    val urls = JsonDecoder().readJsonFromAssets()
    val client = KtorClient.getClient()
    FileIO().createBundleDirectories()

    coroutineScope {
        urls.forEach { url ->
            launch(IO) {
                runCatching {
                    val file = Downloader().downloadVideo(client, url.videoLink)
                    file?.let {
                        val compressedFile = Compressor().compressVideo(file)
                        compressedFile?.let {
                            Encryptor().encrypt(compressedFile)
                        }
                    }
                }.onFailure {
                    println(it.message)
                }
            }
        }
    }
}