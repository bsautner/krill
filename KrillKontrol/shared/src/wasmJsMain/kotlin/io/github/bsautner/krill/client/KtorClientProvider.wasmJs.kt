package io.github.bsautner.krill.client

import io.ktor.client.*
import io.ktor.client.engine.js.*

actual fun getHttpClient(): HttpClientProvider = WasmClient()

class WasmClient: HttpClientProvider {
    override val client: HttpClient = KtorClient.getInstance(Js)
}

