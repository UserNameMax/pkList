package repository

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import api.PokeApi
import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.Pokemon

class PokemonRepositoryImpl(private val pokeApi: PokeApi) : PokemonRepository {
    override suspend fun getPokemons(): Flow<Pokemon> = flow {
        with(pokeApi) {
            for (i in 1..151) {
                val pokemonDto = getPokemon("$i")
                val species = pokemonDto.getSpecies()
                val pokemon = Pokemon(
                    height = pokemonDto.height,
                    id = pokemonDto.id,
                    name = pokemonDto.name,
                    order = pokemonDto.order,
                    weight = pokemonDto.weight,
                    species = species.flavor_text_entries.first().flavor_text.replace("\n", ""),
                    sprites = getBitmap(pokemonDto.sprites.front_default ?: ""),
                    hp = pokemonDto.stats.find { it.stat.name == "hp" }?.base_stat ?: 0,
                    attack = pokemonDto.stats.find { it.stat.name == "attack" }?.base_stat ?: 0,
                    defense = pokemonDto.stats.find { it.stat.name == "defense" }?.base_stat ?: 0,
                    specialAttack = pokemonDto.stats.find { it.stat.name == "special-attack" }?.base_stat ?: 0,
                    specialDefense = pokemonDto.stats.find { it.stat.name == "special-defense" }?.base_stat ?: 0,
                    speed = pokemonDto.stats.find { it.stat.name == "speed" }?.base_stat ?: 0,
                    type = pokemonDto.types.map { it.type.name },
                    abilities = pokemonDto.abilities.map { it.ability.name },
                    baseExperience = pokemonDto.base_experience
                )
                emit(pokemon)
            }
        }
    }

    private val client = HttpClient(Js)

    private suspend fun getBitmap(uri: String): ImageBitmap {
        val byteArray = client.get(uri).readBytes()
        return org.jetbrains.skia.Image.makeFromEncoded(byteArray).toComposeImageBitmap()
    }

}