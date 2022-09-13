package pe.edu.upc.pokedexcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberImagePainter
import pe.edu.upc.pokedexcompose.data.models.Pokemon
import pe.edu.upc.pokedexcompose.data.remote.ApiClient
import pe.edu.upc.pokedexcompose.data.remote.ApiResponse
import pe.edu.upc.pokedexcompose.navigation.Navigation
import pe.edu.upc.pokedexcompose.ui.theme.PokedexComposeTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    var pokemons by mutableStateOf(listOf<Pokemon>())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadItems()
        setContent {
            PokedexComposeTheme {
                Navigation(pokemons)
            }
        }
    }

    private fun loadItems() {
        val pokemonInterface = ApiClient.build()
        val getPokemons = pokemonInterface?.getPokemons()

        getPokemons?.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                pokemons = response.body()!!.results
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.d("MainActivity", t.toString())
            }
        })
    }
}


