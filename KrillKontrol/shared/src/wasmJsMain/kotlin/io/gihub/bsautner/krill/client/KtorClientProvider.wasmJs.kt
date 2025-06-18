package io.gihub.bsautner.krill.client

import io.ktor.client.*
import io.ktor.client.engine.js.*

actual fun getHttpClient(): HttpClientProvider = WasmClient()

class WasmClient: HttpClientProvider {
    override val client: HttpClient = KtorClient.getInstance(Js)
}

