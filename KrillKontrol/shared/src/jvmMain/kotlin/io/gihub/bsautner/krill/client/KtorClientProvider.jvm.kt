package io.gihub.bsautner.krill.client

import io.ktor.client.*
import io.ktor.client.engine.cio.*


actual fun getHttpClient(): HttpClientProvider = DesktopClient()

class DesktopClient: HttpClientProvider {
    override val client: HttpClient =  KtorClient.getInstance(CIO)
}


