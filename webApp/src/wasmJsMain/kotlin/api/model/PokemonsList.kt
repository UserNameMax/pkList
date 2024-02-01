package api.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonsList(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<Result>
)