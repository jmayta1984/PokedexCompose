package pe.edu.upc.pokedexcompose.data.models

class Pokemon (val name: String, val url:String, val height: Int, val weight: Int){
    constructor(): this("","",1,1)
}