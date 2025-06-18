package org.example.project

import com.pi4j.Pi4J
import com.pi4j.context.Context
import com.pi4j.io.gpio.digital.DigitalOutput
import com.pi4j.io.gpio.digital.DigitalState
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


fun mainpi() {

    var pi = Pi4J.newAutoContext()
    println(pi.boardInfo().boardModel.model.name)

    println(pi.providers().digitalOutput().toString())

   // val ledOutput : DigitalOutput = pi.digitalOutput()

    val ledOutput = pi.create(DigitalOutput.newConfigBuilder(pi)
        .id("BCM16")
        .name("LED #16")
        .address(16)
       // .provider(pi.providers().digitalOutput())
        .shutdown(DigitalState.LOW)
        .initial(DigitalState.LOW)

    )
    //ledOutput.blink(1, TimeUnit.SECONDS)
    runBlocking {
      //  delay(5000)

       while (true) {
            println(ledOutput.state().toString())
            if (ledOutput.isLow) {
                ledOutput.high()
            } else {
                ledOutput.low()
            }
            delay(5000)
        //    println("$i")
        }
    }

}

fun main() {

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}
fun Application.module() {

    install(DefaultHeaders) {
        header("X-Engine", "Ktor")
    }
    install(CORS) {
        anyHost()
    }

    routing {
        staticResources("/static", "static", index = "index.html")

        route("/test", HttpMethod.Get) {

            handle {
                val t = Toggle()
                t.test()
                call.respondText("Hello World!")
            }

        }

    }

}

object PiContextProvider  {
    private lateinit var pi : Context
    private lateinit var bcm16: DigitalOutput

    fun getContext() : Context {
        if (! ::pi.isInitialized) {
            pi = Pi4J.newAutoContext()
        }
        return pi
    }

    fun getPin() : DigitalOutput {
        if (! ::bcm16.isInitialized) {
            bcm16 = pi.create(DigitalOutput.newConfigBuilder(pi)
                .id("BCM16")
                .name("LED #16")
                .address(16)
                // .provider(pi.providers().digitalOutput())
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW)
            )
        }
        return bcm16

    }

}

class Toggle() {

    fun test() {
        val pi = PiContextProvider.getContext()
        println(pi.boardInfo().boardModel.model.name)

        println(pi.providers().digitalOutput().toString())

        val ledOutput = PiContextProvider.getPin()

                if (ledOutput.isLow) {
                    ledOutput.high()
                } else {
                    ledOutput.low()
                }



    }
}