package dev.macindoe.quiz.model

data class Quiz(
    val id: String,
    val description: String,
    val questions: List<Question>
)