package io.gihub.bsautner.krill.client

import io.ktor.client.*
import io.ktor.client.engine.android.*

actual fun getHttpClient(): HttpClientProvider = AndroidClient()

class AndroidClient: HttpClientProvider {
    override val client: HttpClient =  KtorClient.getInstance(Android)
}