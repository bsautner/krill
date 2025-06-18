package io.github.bsautner.krill

import io.gihub.bsautner.krill.client.myJson
import io.github.bsautner.krill.di.krillServerModule
import io.github.bsautner.krill.pi.PiManager
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin


fun main() {

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}
fun Application.module() {

    install(Koin) {
        modules(krillServerModule)
    }

    val pi by inject<PiManager>()

   install(CORS) {
        anyHost()
    }
    install (ContentNegotiation) {
        json(myJson)
    }

    routing {
        staticResources("/static", "static", index = "index.html")

        route("/test", HttpMethod.Get) {

            handle {

                pi.togglePin()
                call.respondText("Hello World!")
            }

        }

    }

}
