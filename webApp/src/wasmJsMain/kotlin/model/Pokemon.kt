package model

import androidx.compose.ui.graphics.ImageBitmap

data class Pokemon(
    val height: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val weight: Int,
    val species: String,
    val sprites: ImageBitmap,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int,
    val type: List<String>,
    val abilities: List<String>,
    val baseExperience: Int
)
