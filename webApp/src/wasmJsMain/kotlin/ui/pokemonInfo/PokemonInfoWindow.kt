package ui.pokemonInfo

import com.arkivanov.decompose.ComponentContext
import model.Pokemon

class PokemonInfoWindow(
    componentContext: ComponentContext,
    val pokemon: Pokemon,
    val onDismiss: () -> Unit
) : ComponentContext by componentContext {
}