package io.github.bsautner.krill.client

import io.github.bsautner.krill.pi.GpioPin
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class KrillKtorClient(private val client: HttpClient)   {

    private val host = "pi-krill.local"
    private var session: WebSocketSession? = null
    private var listenJob: Job? = null

    suspend fun updatePin(pin: GpioPin) {

        client.request("http://$host:8080/pin") {
            method = HttpMethod.Post
            contentType(ContentType.Application.Json)
            setBody(pin)
        }

    }

    suspend fun getHeader() : List<GpioPin> {
        val response: HttpResponse = client.request("http://$host:8080/header") {}
        return response.body()
    }

     fun start(callback : suspend (GpioPin) -> Unit) {
        if (listenJob?.isActive == true) return

        listenJob = CoroutineScope(Dispatchers.Default).launch {
            try {
                connectAndListen(callback)

            } catch (e: Exception) {
                println("WebSocket connection failed: ${e.message}")
            }
        }
    }

    suspend fun connectAndListen(callback : suspend (GpioPin) -> Unit) {
        client.webSocket("ws://$host:8080/ws/pin_events") {
            println("WebSocket connected.")
            try {
                for (frame in incoming) {
                    if (frame is Frame.Text) {
                        val pin = myJson.decodeFromString<GpioPin>(frame.readText())
                        println("Gpio Pin: $pin")
                        callback(pin)
                    }
                }
            } catch (e: Exception) {
                println("WebSocket error: ${e.message}")
            }
        }
        println("WebSocket disconnected.")
    }

}