package repository

import kotlinx.coroutines.flow.Flow
import model.Pokemon

interface PokemonRepository {
    suspend fun getPokemons(): Flow<Pokemon>
}