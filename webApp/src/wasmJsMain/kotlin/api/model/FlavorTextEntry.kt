package api.model

import kotlinx.serialization.Serializable

@Serializable
data class FlavorTextEntry(
    val flavor_text: String,
    val language: Language,
    val version: Version
)