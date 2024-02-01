package api.model

import kotlinx.serialization.Serializable

@Serializable
data class Language(
    val name: String,
    val url: String
)