package io.github.bsautner.krill

import com.pi4j.io.gpio.digital.DigitalStateChangeListener
import io.github.bsautner.krill.client.myJson
import io.github.bsautner.krill.di.krillServerModule
import io.github.bsautner.krill.pi.GpioPin
import io.github.bsautner.krill.pi.PiManager
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin

//class ChangeListener : DigitalStateChangeListener {
//    override fun onDigitalStateChange(event: DigitalStateChangeEvent<*>?) {
//
//        println("Digital state change event $event")
//    }
//
//}

fun main() {

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}
fun Application.module() {
    val messageResponseFlow = MutableSharedFlow<GpioPin>()
    val sharedFlow = messageResponseFlow.asSharedFlow()
    install(Koin) {
        modules(krillServerModule)
    }
    val pi by inject<PiManager>()

    install(WebSockets)

    install(CORS) {
        anyHost()
    }
    install (ContentNegotiation) {
        json(myJson)
    }

    routing {

        webSocket("/ws/pin_events") {
            println("** WS ONCONNECT")
            launch {
                send(Frame.Text(myJson.encodeToString(GpioPin(0, "on"))))
            }

            val p16 = pi.getPin()
            val l = DigitalStateChangeListener {

                launch {
                    it?.let {
                        val name = it.source().id
                        val state = it.state().name
                        println("pin change detected $name $state $it")
                        val pin = GpioPin(0, name = name, state = state)
                        send(Frame.Text(myJson.encodeToString(pin)))
                    }

                }

            }
            p16.addListener(l)
            // Keep the socket open
            for (frame in incoming) {
                // Just consume pings or do nothing
            }

        }


       // staticResources("/", "static", index = "index.html")

        route("/test", HttpMethod.Get) {

            handle {

                pi.togglePin()
                call.respondText("Hello World!")
            }

        }

//        route("/gpio", HttpMethod.Get) {
//
//            handle {
//
//              call.respond(HttpStatusCode.OK, pi.getPins())
//            }
//
//        }

    }

}
