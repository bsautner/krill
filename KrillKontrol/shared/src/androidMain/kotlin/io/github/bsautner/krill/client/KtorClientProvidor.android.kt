package io.github.bsautner.krill.client

import io.ktor.client.*
import io.ktor.client.engine.cio.*


actual fun getHttpClient(): HttpClientProvider = AndroidClient()

class AndroidClient: HttpClientProvider {
    override val client: HttpClient =  KtorClient.getInstance(CIO)
}