package pe.edu.upc.pokedexcompose.ui.pokemonlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import pe.edu.upc.pokedexcompose.data.models.Pokemon

@Composable
fun MyApp(
    pokemons: List<Pokemon>,
    selectPokemon: (Int) -> Unit
) {
    Scaffold {
        PokemonList(pokemons, selectPokemon)
    }
}

@Composable
fun PokemonList(
    pokemons: List<Pokemon>,
    selectPokemon: (Int) -> Unit
) {
    LazyColumn {
        items(pokemons) { pokemon ->
            PokemonRow(pokemon, selectPokemon)
        }
    }
}

@Composable
fun PokemonRow(
    pokemon: Pokemon,
    selectPokemon: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                selectPokemon(1)
            },
        elevation = 2.dp
    ) {
        Column {
            Text(text = pokemon.name)
            Spacer(modifier = Modifier.height(8.dp))
            PokemonImage()
        }
    }
}

@Composable
fun PokemonImage() {
    Image(
        painter = rememberImagePainter("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"),
        contentDescription = null,
        modifier = Modifier.size(64.dp),
        contentScale = ContentScale.Crop
    )
}