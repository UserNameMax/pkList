import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.window.CanvasBasedWindow
import api.PokeApiImpl
import api.model.PokemonDto
import api.utils.httpClient
import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.Pokemon
import org.jetbrains.skia.Bitmap
import repository.PokemonRepositoryImpl

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val httpClient = httpClient()
    val pokeApi = PokeApiImpl(httpClient)
    val repository = PokemonRepositoryImpl(pokeApi)
    CanvasBasedWindow {
        var pokemonsList by remember { mutableStateOf(listOf<Pokemon>()) }
        MainContent(pokemonsList)
        LaunchedEffect(Unit) {
            launch {
                repository.getPokemons().collect {
                    pokemonsList = pokemonsList + it
                }
            }
        }
    }
}

@Composable
fun MainContent(pokemonsList: List<Pokemon>) {
    LazyColumn {
        items(pokemonsList) {
            Image(
                modifier = Modifier,
                contentDescription = null,
                painter = BitmapPainter(image = it.sprites)
            )
        }
    }

}