package api

import api.model.Pokemon
import api.model.PokemonsList
import api.utils.httpClient
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class PokeApiImpl(private val client: HttpClient) : PokeApi {
    val pokeApiUrl = "https://pokeapi.co/api/v2"
    override suspend fun getListPokemons(): PokemonsList = client.get("$pokeApiUrl/pokemon").body()
    override suspend fun getPokemon(id: String): Pokemon = client.get("$pokeApiUrl/pokemon/$id").body()
}