package pe.edu.upc.pokedexcompose.ui.pokemondetails

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch
import pe.edu.upc.pokedexcompose.data.models.Pokemon
import pe.edu.upc.pokedexcompose.data.remote.ApiClient
import pe.edu.upc.pokedexcompose.data.remote.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonViewModel : ViewModel() {

    val pokemonInterface = ApiClient.build()

    private var _pokemon = MutableLiveData<Pokemon>()
    val pokemon get() = _pokemon

    fun getPokemon(position: Int) {
        viewModelScope.launch {
            try {
                val getPokemon = pokemonInterface?.getPokemonDetail(position)
                getPokemon?.enqueue(object : Callback<Pokemon> {

                    override fun onResponse(
                        call: Call<Pokemon>,
                        response: Response<Pokemon>
                    ) {
                        pokemon.postValue(response.body())
                    }

                    override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                        Log.d("MainActivity", t.toString())
                    }
                })
            } catch (e: Exception) {
                Log.e("ViewModel: Get Pokemons", e.toString())
            }
        }
    }
}
