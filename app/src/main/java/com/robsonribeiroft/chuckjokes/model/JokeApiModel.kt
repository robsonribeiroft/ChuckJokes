package com.robsonribeiroft.chuckjokes.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JokeApiModel(
    @SerialName("id") val id: String,
    @SerialName("value") val joke: String
)