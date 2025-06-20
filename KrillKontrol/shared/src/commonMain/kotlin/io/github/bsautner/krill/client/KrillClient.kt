package io.github.bsautner.krill.client

import io.github.bsautner.krill.pi.GpioPin
import io.ktor.client.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

interface KrillClient {
    suspend fun testGet() : String
    suspend fun start(callback : suspend (GpioPin) -> Unit)
}

class DefaultKrillClient(private val client: HttpClient

) : KrillClient  {
    private val host = "pi-krill.local"
    private var session: WebSocketSession? = null
    private var listenJob: Job? = null

    override suspend fun testGet() :String {
        val response: HttpResponse = client.request("http://$host:8080/test") {
            method = HttpMethod.Get
        }
        return response.bodyAsText()
    }
    override suspend fun start(callback : suspend (GpioPin) -> Unit) {
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