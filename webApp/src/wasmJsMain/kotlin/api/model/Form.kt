package api.model

import kotlinx.serialization.Serializable

@Serializable
data class Form(
    val name: String,
    val url: String
)