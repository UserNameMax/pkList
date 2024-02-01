package api.model

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val name: String,
    val url: String
)