package pe.edu.upc.pokedexcompose.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pe.edu.upc.pokedexcompose.data.models.Pokemon

@Database(entities = [Pokemon::class], version = 2, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    companion object {
        fun getInstance(context: Context): PokemonDatabase {
            val db = Room.databaseBuilder(
                context,
                PokemonDatabase::class.java,
                "pokemon-db"
            ).allowMainThreadQueries().build()
            return db
        }
    }
}