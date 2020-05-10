package dev.macindoe.quiz

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

fun Route.v1QuizRest() {
    route("v1") {
        get("/quiz/{quizId}") {
            val quizId = call.parameters["quizId"]!!
            call.respond(QuizRepositoryImpl().getQuiz(quizId))
        }
    }
}