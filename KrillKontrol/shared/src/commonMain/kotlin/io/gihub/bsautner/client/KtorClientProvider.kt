package io.gihub.bsautner.client

import io.ktor.client.*


interface  HttpClientProvider {
    val client: HttpClient
}

expect fun getHttpClient(): HttpClientProvider


