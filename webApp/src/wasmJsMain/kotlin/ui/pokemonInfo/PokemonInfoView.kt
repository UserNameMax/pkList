package ui.pokemonInfo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.Pokemon

@Composable
fun PokemonInfoView(pokemonInfoWindow: PokemonInfoWindow) {
    PokemonInfoView(pokemonInfoWindow.pokemon, pokemonInfoWindow.onDismiss)
}

@Composable
fun PokemonInfoView(pokemon: Pokemon, onDismiss: () -> Unit) {
    Box(Modifier.background(Color.Black.copy(alpha = 0.7f))) {
        Column {
            Row(Modifier.fillMaxWidth().fillMaxHeight(0.5f)) {
                Image(
                    modifier = Modifier.fillMaxWidth(0.5f).fillMaxHeight(),
                    contentDescription = null,
                    painter = BitmapPainter(image = pokemon.sprites)
                )
                Column {
                    Text(text = pokemon.name, color = Color(0xFF7B001C), fontSize = 40.sp)
                    Spacer(Modifier.height(10.dp))
                    Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceAround) {
                        Text("attack = ${pokemon.height}", color = Color.White, fontSize = 30.sp)
                        Text("defense = ${pokemon.defense}", color = Color.White, fontSize = 30.sp)
                        Text("attack = ${pokemon.attack}", color = Color.White, fontSize = 30.sp)
                        Text("defense = ${pokemon.defense}", color = Color.White, fontSize = 30.sp)
                        Text("special attack = ${pokemon.specialAttack}", color = Color.White, fontSize = 30.sp)
                        Text("special defense = ${pokemon.specialDefense}", color = Color.White, fontSize = 30.sp)
                        Text("speed = ${pokemon.speed}", color = Color.White, fontSize = 30.sp)
                    }
                }
            }
            Text(modifier = Modifier.padding(10.dp), text = pokemon.species, color = Color.White, fontSize = 30.sp)
        }
        Box(modifier = Modifier.fillMaxWidth().padding(5.dp), contentAlignment = Alignment.TopEnd) {
            Box(modifier = Modifier.size(10.dp).background(Color.Red).clickable { onDismiss() })
        }
    }
}