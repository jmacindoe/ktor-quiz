package dev.macindoe.ktor.model

data class Quiz(
    val id: String,
    val description: String,
    val questions: List<Question>
)