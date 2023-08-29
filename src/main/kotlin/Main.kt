import com.google.gson.Gson
import io.ktor.client.*
import kotlinx.coroutines.runBlocking
import model.LPsAudioModel
import model.LPsImageModel
import model.LPsVideoModel
import remote.KtorClient
import utils.*
import java.io.File

/** @author Nabi Ahmad
 * [github.com/csnabiahmad]
 * Mon, 29 May, 2023
 */


private var urlsVideos: LPsVideoModel = LPsVideoModel()
private var urlsImages: LPsImageModel = LPsImageModel()
private var urlsAudio: LPsAudioModel = LPsAudioModel()
lateinit var client: HttpClient
lateinit var videoLinks: List<LPsVideoModel.LPsVideoModelItem>

fun main() = runBlocking<Unit> {
//    init()
    generateSLO() // creating CSV from SLO.json
//    downloadImages() // for images
//    downloadAudios() // for audios
//    downloadVideos() // for videos
}

fun init() {
    val assetFile = File(lpImagesStage)
    val jsonString = assetFile.readText()


    println(" *** Video Download, Compression & Encryption Script *** ")
//    urlsVideos = JsonDecoder().readJsonFromAssets(fileName = lpData, LPsVideoModel::class.java)
//    urlsImages = JsonDecoder().readJsonFromAssets(fileName = lpImagesStage, LPsImageModel::class.java)
    urlsAudio = JsonDecoder().readJsonFromAssets(fileName = lpAudiosStage, LPsAudioModel::class.java)
    client = KtorClient.getClient()
    FileIO().createBundleDirectories()
    videoLinks = urlsVideos.filter { it.videoLink.isNotEmpty() }
}
fun generateSLO(){
    SloGenerator().generateSLOFile()
}
fun downloadImages() {
    println("Total: ${urlsImages.size}")
    Coroutines.executeCoroutineIO {
        urlsImages.forEachIndexed { index, item ->
            println("Index: " + index)
            Downloader().startDownload(client, item.link!!)
        }
    }
}
fun downloadAudios() {
    println("Total: ${urlsAudio.size}")
    Coroutines.executeCoroutineIO {
        urlsAudio.forEachIndexed { index, item ->
            println("Index: " + index)
            Downloader().startDownload(client, item.link!!)
        }
    }
}
fun downloadVideos() {
    println("Total: ${videoLinks.size}")
    Coroutines.executeCoroutineIO {
        videoLinks.forEachIndexed { index, item ->
            println("Index: " + index)
            println("Item: " + Gson().toJson(item))
            item.videoLink.forEach {
                runCatching {
                    val downloaded = Downloader().startDownload(client, it)
                    downloaded?.let {
                        val compressedFile = Compressor().startCompression(downloaded)
                        compressedFile?.let {
                            Encryptor().encrypt(compressedFile)
                        }
                        println("File: $compressedFile")
                    }
                }.onFailure {
                    return@executeCoroutineIO
                }.onSuccess {
                    return@onSuccess
                }
            }
        }
    }
}