import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    LazyVerticalGrid(
        columns = GridCells.Adaptive(250.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(pokemonsList) {
            PkCard(
                modifier = Modifier.width(250.dp).height(280.dp).shadow(1.dp),
                pokemon = it
            )
        }
    }
}

@Composable
fun PkCard(modifier: Modifier = Modifier, pokemon: Pokemon) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier.fillMaxWidth().heightIn(min = 120.dp),
            contentDescription = null,
            painter = BitmapPainter(image = pokemon.sprites)
        )
        Spacer(Modifier.height(10.dp))
        Column(modifier = Modifier.fillMaxWidth().padding(start = 10.dp)) {
            Text(text = pokemon.name, color = Color(0xFF7B001C), fontSize = 20.sp)
            Spacer(Modifier.height(5.dp))
            Text(
                text = pokemon.species,
                color = Color.Gray,
            )
        }
    }
}