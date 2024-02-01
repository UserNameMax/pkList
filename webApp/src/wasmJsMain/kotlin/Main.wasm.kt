import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.CanvasBasedWindow
import androidx.compose.ui.window.Dialog
import api.PokeApiImpl
import api.utils.httpClient
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import model.Pokemon
import repository.PokemonRepositoryImpl
import ui.list.PokemonList
import ui.list.PokemonListView

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val httpClient = httpClient()
    val pokeApi = PokeApiImpl(httpClient)
    val repository = PokemonRepositoryImpl(pokeApi)
    val lifecycle = LifecycleRegistry()
    val pokemonsList = PokemonList(
        componentContext = DefaultComponentContext(lifecycle),
        pokemonRepository = repository
    )
    CanvasBasedWindow {
        PokemonListView(pokemonsList)
    }
}