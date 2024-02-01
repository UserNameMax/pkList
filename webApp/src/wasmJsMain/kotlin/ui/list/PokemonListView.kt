package ui.list

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import model.Pokemon
import ui.pokemonInfo.PokemonInfoView

@Composable
fun PokemonListView(pokemonsList: PokemonList) {
    val list by pokemonsList.pokemonsList.collectAsState()
    val window by pokemonsList.window.subscribeAsState()
    MainContent(list) { pokemonsList.openPokemonInfo(it) }
    AnimatedVisibility(
        visible = window.child != null,
        enter = fadeIn() + slideInVertically() + expandVertically(),
        exit = fadeOut() + slideOutVertically() + shrinkHorizontally()
    ) {
        window.child?.instance?.run {
            Dialog(onDismissRequest = pokemonsList::closePokemonInfo) {
                PokemonInfoView(this)
            }
        }
    }
}

@Composable
fun MainContent(pokemonsList: List<Pokemon>, onClick: (Pokemon) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(250.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(pokemonsList) {
            PkCard(
                modifier = Modifier.width(250.dp).height(280.dp).shadow(1.dp).clickable { onClick(it) },
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