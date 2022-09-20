package pe.edu.upc.pokedexcompose.ui.pokemonlist

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pe.edu.upc.pokedexcompose.data.local.PokemonDatabase
import pe.edu.upc.pokedexcompose.data.models.Pokemon
import pe.edu.upc.pokedexcompose.data.remote.ApiClient
import pe.edu.upc.pokedexcompose.data.remote.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonListViewModel(application: Application) : AndroidViewModel(application) {
    val pokemonInterface = ApiClient.build()
    val pokemonDao = PokemonDatabase.getInstance(application).pokemonDao()

    private var _pokemons = MutableLiveData<List<Pokemon>>()
    val pokemons get() = _pokemons

    init {
        getPokemons()
    }

    fun insertPokemon(pokemon: Pokemon){
        pokemonDao.insertPokemon(pokemon)
    }

    fun deletePokemon(pokemon: Pokemon){
        pokemonDao.deletePokemon(pokemon)
    }

    fun getPokemons() {
        viewModelScope.launch {
            try {
                val getPokemons = pokemonInterface?.getPokemons()
                getPokemons?.enqueue(object : Callback<ApiResponse> {

                    override fun onResponse(
                        call: Call<ApiResponse>,
                        response: Response<ApiResponse>
                    ) {
                        pokemons.postValue(response.body()!!.results)
                    }

                    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                        Log.d("MainActivity", t.toString())
                    }
                })
            } catch (e: Exception) {
                Log.e("ViewModel: Get Pokemons", e.toString())
            }
        }
    }
}