package io.gihub.bsautner.krill.client

import io.ktor.client.*
import kotlinx.serialization.json.Json


interface  HttpClientProvider {
    val client: HttpClient
}

expect fun getHttpClient(): HttpClientProvider


val myJson = Json {
     ignoreUnknownKeys = true
     prettyPrint = false
     isLenient = true
}