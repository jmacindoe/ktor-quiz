package dev.macindoe.ktor

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readText
import io.ktor.jackson.jackson
import io.ktor.response.*
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.websocket.WebSockets
import io.ktor.websocket.webSocket

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(WebSockets)
    install(CallLogging)
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        webSocket("/ws") {
            for (frame in incoming) {
                if (frame is Frame.Text) {
                    val text = frame.readText().trim()
                    outgoing.send(Frame.Text("You said: $text"))
                }
            }
        }
        route("v1") {
            get("/quiz/{quizId}") {
                val quizId = call.parameters["quizId"]!!
                call.respond(QuizRepositoryImpl().getQuiz(quizId))
            }
        }
    }
}

