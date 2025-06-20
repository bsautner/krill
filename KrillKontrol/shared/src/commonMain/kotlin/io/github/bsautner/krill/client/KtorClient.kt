package io.github.bsautner.krill.client

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.json.*

object KtorClient {

    lateinit var c: HttpClient

    fun <T : HttpClientEngineConfig> getInstance(engine:  HttpClientEngineFactory<T> ): HttpClient {
        if (!::c.isInitialized) {
            c = HttpClient(engine) {

                install(WebSockets)

                install(ContentNegotiation) {
                    json(myJson)
                }
            }
        }
        return c
    }


}