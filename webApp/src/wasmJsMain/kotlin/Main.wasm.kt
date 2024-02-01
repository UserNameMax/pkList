import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import api.PokeApiImpl
import api.model.Pokemon
import api.utils.httpClient
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val httpClient = httpClient()
    val pokeApi = PokeApiImpl(httpClient)
    CanvasBasedWindow {
        var pokemonsList by remember { mutableStateOf(listOf<Pokemon>()) }
        Column {
            Text("Hello World")
            pokemonsList.forEach { Text(it.name) }
        }
        LaunchedEffect(Unit) {
            launch {
                for (i in 1..20) {
                    pokemonsList = pokemonsList + pokeApi.getPokemon("$i")
                }
            }
        }
    }
}
