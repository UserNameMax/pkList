package api

import api.model.PokemonDto
import api.model.PokemonsList
import api.model.SpeciesDto

interface PokeApi {
    suspend fun getListPokemons(): PokemonsList
    suspend fun getPokemon(id: String): PokemonDto
    suspend fun PokemonDto.getSpecies(): SpeciesDto
}