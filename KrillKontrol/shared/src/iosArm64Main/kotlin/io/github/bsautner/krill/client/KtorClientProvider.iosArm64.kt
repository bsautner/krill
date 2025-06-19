package io.github.bsautner.krill.client

import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.contentnegotiation.*


actual fun getHttpClient(): HttpClientProvider = IosClient()

class IosClient : HttpClientProvider {
    override val client: HttpClient = HttpClient(Darwin) {
        install(ContentNegotiation) {
            myJson
        }

    }
}