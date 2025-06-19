package io.gihub.bsautner.krill.client

actual fun getHttpClient(): HttpClientProvider = IosClient()

class IosClient : HttpClientProvider {
    override val client: HttpClient = HttpClient(Darwin) {
        install(ContentNegotiation) {
            myJson
        }

    }
}