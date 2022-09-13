package pe.edu.upc.pokedexcompose.data.remote

import pe.edu.upc.pokedexcompose.data.models.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonInterface {

    @GET("pokemon")
    fun getPokemons(): Call<ApiResponse>

    @GET("pokemon/{position}")
    fun getPokemonDetail(@Path("position") position: Int): Call<Pokemon>
}