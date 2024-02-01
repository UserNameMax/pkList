package api

import api.model.Pokemon
import api.model.PokemonsList

interface PokeApi {
    suspend fun getListPokemons(): PokemonsList
    suspend fun getPokemon(id: String): Pokemon
}