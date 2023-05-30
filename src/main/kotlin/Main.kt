
import kotlinx.coroutines.runBlocking
import remote.KtorClient
import utils.Coroutines
import utils.Downloader
import utils.FileIO
import utils.JsonDecoder

/** @author Nabi Ahmad
 * [github.com/csnabiahmad]
 * Mon, 29 May, 2023
 */


fun main() = runBlocking<Unit> {
    println(" *** Video Download, Compression & Encryption Script *** ")
    FileIO().createBundleDirectories()
    val teacherTrainingVideoModel = JsonDecoder().readJsonFromAssets()
    teacherTrainingVideoModel.onEach { item->
        Coroutines.executeCoroutineIO {
            println("=== Teacher Training : ${item.videoLink} ===")
            runCatching {
                Downloader().downloadVideo(
                    client = KtorClient.getClient(),
                    url = item.videoLink,
                )
            }.onFailure {
                println("=== Teacher Training Failed: ${item.videoLink} ===")
            }.onSuccess {
                println("=== Teacher Training Success: ${item.videoLink} ===")
            }
        }
    }
}