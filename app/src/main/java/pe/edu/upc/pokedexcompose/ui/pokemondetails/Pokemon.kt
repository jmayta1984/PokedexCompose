package pe.edu.upc.pokedexcompose.ui.pokemondetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import pe.edu.upc.pokedexcompose.data.models.Pokemon

@Composable
fun PokemonDetails(viewModel: PokemonViewModel) {

    val pokemon: Pokemon by viewModel.pokemon.observeAsState(Pokemon())

    if (pokemon.id != 0){
        Column {
            PokemonImage(pokemon)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = pokemon.name)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "${pokemon.height}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "${pokemon.weight}")
        }
    }

}

@Composable
fun PokemonImage(pokemon: Pokemon) {



    Image(
        painter = rememberImagePainter("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.id}.png"),
        contentDescription = null,
        modifier = Modifier.height(128.dp),
        contentScale = ContentScale.Crop
    )
}