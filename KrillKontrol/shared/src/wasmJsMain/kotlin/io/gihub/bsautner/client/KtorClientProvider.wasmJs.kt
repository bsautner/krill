package io.gihub.bsautner.client

import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

actual fun getHttpClient(): HttpClientProvider = WasmClient()

class WasmClient: HttpClientProvider {
    override val client: HttpClient = WasmKtorClient.getInstance()
}

object WasmKtorClient {

    lateinit var c: HttpClient

    fun getInstance(): HttpClient {
        if (!::c.isInitialized) {
            c = HttpClient(Js) {
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
