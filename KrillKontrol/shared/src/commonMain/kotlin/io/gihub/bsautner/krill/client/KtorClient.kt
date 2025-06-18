package io.gihub.bsautner.krill.client

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

object KtorClient {

    lateinit var c: HttpClient

    fun <T : HttpClientEngineConfig> getInstance(engine:  HttpClientEngineFactory<T> ): HttpClient {
        if (!::c.isInitialized) {
            c = HttpClient(engine) {
                install(ContentNegotiation) {
                    json(myJson)
                }
            }
        }
        return c
    }

}