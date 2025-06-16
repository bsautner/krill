package org.example.project

import com.pi4j.Pi4J
import com.pi4j.io.gpio.digital.DigitalOutput
import com.pi4j.io.gpio.digital.DigitalState
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


fun main() {

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
//    embeddedServer(Netty, port = SERVER_PORT, host = "pi-krill.local", module = Application::module)
//        .start(wait = true)
fun Application.module() {
    routing {
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }
    }
}