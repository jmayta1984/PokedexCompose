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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import pe.edu.upc.pokedexcompose.data.models.Pokemon

@Composable
fun MyApp(
    viewModel: PokemonListViewModel,
    selectPokemon: (Int) -> Unit
) {
    Scaffold {
        PokemonList(viewModel, selectPokemon)
    }
}

@Composable
fun PokemonList(
    viewModel: PokemonListViewModel,
    selectPokemon: (Int) -> Unit
) {

    val pokemons: List<Pokemon> by viewModel.pokemons.observeAsState(listOf())

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
    val numberPosition = pokemon.url.indexOf('2')
    val positionString = pokemon.url.filter { it.isDigit() }
    val position = Integer.parseInt(positionString)

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                selectPokemon(position)
            },
        elevation = 2.dp
    ) {
        Column {
            Text(text = pokemon.name)
            Spacer(modifier = Modifier.height(8.dp))
            PokemonImage(pokemon)
        }
    }
}

@Composable
fun PokemonImage(pokemon: Pokemon) {
    val positionString = pokemon.url.filter { it.isDigit() }

    Image(
        painter = rememberImagePainter("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${positionString}.png"),
        contentDescription = null,
        modifier = Modifier.size(64.dp),
        contentScale = ContentScale.Crop
    )
}