package org.example.project

import com.pi4j.Pi4J
import com.pi4j.io.gpio.digital.DigitalOutput
import com.pi4j.io.gpio.digital.DigitalState
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.routing.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.io.File


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

    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}
fun Application.module() {
    install(DefaultHeaders) {
    header("X-Engine", "Ktor") // will send this header with each response
}

    routing {
        staticResources("/", "static", index = "index.html")

    }

    environment.monitor.subscribe(ApplicationStarted) {
        File("static").walkTopDown().forEach {
            if (it.extension == "wasm") {
                it.setExecutable(true)
            }
        }
    }



}