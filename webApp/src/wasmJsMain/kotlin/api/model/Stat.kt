package api.model

import kotlinx.serialization.Serializable

@Serializable
data class Stat(
    val base_stat: Int,
    val effort: Int,
    val stat: StatX
)