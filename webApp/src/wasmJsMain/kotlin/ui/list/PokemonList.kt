package ui.list

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.*
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import model.Pokemon
import repository.PokemonRepository
import ui.pokemonInfo.PokemonInfoWindow

class PokemonList(componentContext: ComponentContext, private val pokemonRepository: PokemonRepository) :
    ComponentContext by componentContext {
    private val mutablePokemonList = MutableStateFlow(listOf<Pokemon>())
    val pokemonsList = mutablePokemonList.asStateFlow()

    private val windowNavigation = SlotNavigation<WindowConfig>()

    val window: Value<ChildSlot<WindowConfig, PokemonInfoWindow>> = childSlot(
        source = windowNavigation,
        serializer = WindowConfig.serializer()
    ) { configuration, componentContext ->
        when (configuration) {
            is WindowConfig.PokemonWindow -> PokemonInfoWindow(
                componentContext = componentContext,
                pokemon = configuration.pokemon,
                onDismiss = ::closePokemonInfo
            )
        }
    }

    init {
        loadPokemonList()
    }

    private fun loadPokemonList() {
        CoroutineScope(Dispatchers.Default).launch {
            pokemonRepository.getPokemons().collect { newPokemon ->
                mutablePokemonList.update { it + newPokemon }
            }
        }
    }

    fun openPokemonInfo(pokemon: Pokemon) {
        windowNavigation.activate(WindowConfig.PokemonWindow(pokemon))
    }

    fun closePokemonInfo() {
        windowNavigation.dismiss()
    }

    @Serializable
    sealed interface WindowConfig {
        @Serializable
        data class PokemonWindow(val pokemon: Pokemon) : WindowConfig
    }
}