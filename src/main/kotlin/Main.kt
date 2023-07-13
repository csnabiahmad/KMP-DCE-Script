import com.google.gson.Gson
import io.ktor.client.*
import kotlinx.coroutines.runBlocking
import model.LPsImageModel
import model.LPsVideoModel
import remote.KtorClient
import utils.*

/** @author Nabi Ahmad
 * [github.com/csnabiahmad]
 * Mon, 29 May, 2023
 */


lateinit var urls: LPsVideoModel
lateinit var urlsImages: LPsImageModel
lateinit var client: HttpClient
lateinit var videoLinks: List<LPsVideoModel.LPsVideoModelItem>

fun main() = runBlocking<Unit> {
    init()
    downloadImages() // for images
//    downloadVideos() // for videos
}

fun init() {
    println(" *** Video Download, Compression & Encryption Script *** ")
    urls = JsonDecoder().readJsonFromAssets(fileName = lpData, LPsVideoModel::class.java)
    urlsImages = JsonDecoder().readJsonFromAssets(fileName = lpImages, LPsImageModel::class.java)
    client = KtorClient.getClient()
    FileIO().createBundleDirectories()
    videoLinks = urls.filter { it.videoLink.isNotEmpty() }
}

fun downloadImages() {
    println("Total: ${urlsImages.size}")
    Coroutines.executeCoroutineIO {
        urlsImages.forEachIndexed { index, item ->
            println("Index: " + index)
            Downloader().downloadVideo(client, item.link!!)
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
                    val downloaded = Downloader().downloadVideo(client, it)
                    downloaded?.let {
                        val compressedFile = Compressor().compressVideo(downloaded)
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