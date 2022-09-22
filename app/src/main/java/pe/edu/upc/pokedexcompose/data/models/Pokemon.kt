package pe.edu.upc.pokedexcompose.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Pokemon(

    @PrimaryKey
    val name: String,

    @ColumnInfo
    val url: String,

    @ColumnInfo
    val height: Int,

    @ColumnInfo
    val weight: Int,


    ) {
    constructor() : this("", "", 0, 0)

    @Ignore
    var favorite: Boolean = false

    @Ignore
    var id: Int = 0
}