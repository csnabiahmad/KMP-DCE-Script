package remote

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*

/** @author Nabi Ahmad
 * [github.com/csnabiahmad]
 * Mon, 29 May, 2023
 */

object KtorClient {
    fun getClient() = HttpClient(OkHttp) {
        engine {
            config {
                followRedirects(true)
            }
        }
    }
}