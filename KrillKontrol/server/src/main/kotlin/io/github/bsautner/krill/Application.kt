package io.github.bsautner.krill

import com.pi4j.io.gpio.digital.DigitalStateChangeListener
import io.github.bsautner.krill.client.myJson
import io.github.bsautner.krill.di.krillServerModule
import io.github.bsautner.krill.pi.GpioPin
import io.github.bsautner.krill.pi.PiManager
import io.github.bsautner.krill.pi.State
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import java.io.File



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
    val piPins = pi.getPins()
    piPins.forEach {
        println("${it.name} - ${it.state()}")
        updatePinStore(it.name, State.valueOf(it.state().name))
    }
    val json = File("/etc/krill/gpio.json")
    val pins =  myJson.decodeFromString<MutableList<GpioPin>>(json.readText())
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

                pins.forEach {
                    println("${it.name} - ${it.state}")
                    send(Frame.Text(myJson.encodeToString( it)))

                }


            }

            val piPins = pi.getPins()
            piPins.forEach { pin ->
                val l = DigitalStateChangeListener {

                    launch {
                        it?.let {
                            val name = it.source().id

                            val spin = pins.find { p -> p.name == name }
                            val state = State.valueOf(it.state().name)
                            spin?.state = state
                            println("pin change detected $name $state $it")
                            send(Frame.Text(myJson.encodeToString(spin)))
                            updatePinStore(name, state)


                        }

                    }

                }
                pin.addListener(l)
            }

            // Keep the socket open
            for (frame in incoming) {
                // Just consume pings or do nothing
            }

        }


        staticResources("/", "static", index = "index.html")

        route("/pin", HttpMethod.Post) {

            handle {
                val pin = call.receive<GpioPin>()

                pi.setPinState(pin)

                call.respond(HttpStatusCode.OK, pin)
            }

        }

        route("/header") {
            get {
                val json = File("/etc/krill/gpio.json")
                if (json.exists()) {
                    val pins =  myJson.decodeFromString<List<GpioPin>>(json.readText())
                    call.respond(HttpStatusCode.OK, pins)
                } else {
                    call.respond(HttpStatusCode.InternalServerError, "The header file is missing ${json.path}")
                }

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
fun updatePinStore(name: String, value: State) {
    val json = File("/etc/krill/gpio.json")
    val pins =  myJson.decodeFromString<MutableList<GpioPin>>(json.readText())
    pins.find { p -> p.name == name }?.let { gp ->
        gp.state = value
    }
    val update = myJson.encodeToString(pins)
    json.writeText(update)
}