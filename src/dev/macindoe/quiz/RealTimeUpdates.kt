package dev.macindoe.quiz

import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readText
import io.ktor.routing.Route
import io.ktor.websocket.webSocket

/**
 * Provide real time updates to clients about quiz progress
 */
fun Route.realTimeUpdates() {
    webSocket("/ws") {
        for (frame in incoming) {
            if (frame is Frame.Text) {
                val text = frame.readText().trim()
                outgoing.send(Frame.Text("You said: $text"))
            }
        }
    }
}