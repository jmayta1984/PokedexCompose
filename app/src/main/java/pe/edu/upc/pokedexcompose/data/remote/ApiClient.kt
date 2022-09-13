package pe.edu.upc.pokedexcompose.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val API_BASE_URL = "https://pokeapi.co/api/v2/"

    var pokemonInterface: PokemonInterface? = null

    fun build(): PokemonInterface?{
        val builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit = builder.build()

        pokemonInterface = retrofit.create(PokemonInterface::class.java)

        return pokemonInterface as PokemonInterface
    }
}