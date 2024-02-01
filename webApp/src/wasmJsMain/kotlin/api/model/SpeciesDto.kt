package api.model

import kotlinx.serialization.Serializable

@Serializable
data class SpeciesDto(
    val flavor_text_entries: List<FlavorTextEntry>
)