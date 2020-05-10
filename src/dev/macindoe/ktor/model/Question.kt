package dev.macindoe.ktor.model

data class Question(
    val id: String,
    val text: String,
    val hints: List<Hint>
)