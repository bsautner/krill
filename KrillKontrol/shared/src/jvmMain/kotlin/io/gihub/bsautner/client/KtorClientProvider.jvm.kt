package io.gihub.bsautner.client

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


actual fun getHttpClient(): HttpClientProvider = DesktopClient()

class DesktopClient: HttpClientProvider {
    override val client: HttpClient = DesktopKtorClient.getInstance()
}

object DesktopKtorClient {

    lateinit var c: HttpClient

    fun getInstance(): HttpClient {
        if (!::c.isInitialized) {
            c = HttpClient(CIO) {
                install(ContentNegotiation) {
                    json(Json {
                        ignoreUnknownKeys = true
                        prettyPrint = false
                        isLenient = true
                    })
                }
            }
        }
        return c
    }

}
