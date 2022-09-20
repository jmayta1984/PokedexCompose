package pe.edu.upc.pokedexcompose.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import pe.edu.upc.pokedexcompose.data.models.Pokemon

@Dao
interface PokemonDao {

    @Insert
    fun insertPokemon(vararg pokemons: Pokemon)

    @Delete
    fun deletePokemon(vararg pokemons: Pokemon)

    @Query("select * from pokemon")
    fun getAllPokemons(): List<Pokemon>
}