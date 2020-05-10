package dev.macindoe.quiz

import dev.macindoe.quiz.model.Question
import dev.macindoe.quiz.model.Quiz

interface QuizRepository {
    fun getQuiz(quizId: String): Quiz
}

class QuizRepositoryImpl : QuizRepository {
    override fun getQuiz(quizId: String): Quiz {
        val hardcodedQuiz = Quiz(
            quizId,
            "The first ever quiz",
            listOf(
                Question("1", "First Question", emptyList())
            )
        )

        return hardcodedQuiz
    }
}