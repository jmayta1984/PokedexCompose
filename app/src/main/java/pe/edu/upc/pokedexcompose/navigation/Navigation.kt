package pe.edu.upc.pokedexcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pe.edu.upc.pokedexcompose.data.models.Pokemon
import pe.edu.upc.pokedexcompose.ui.pokemonlist.MyApp

@Composable
fun Navigation(pokemons: List<Pokemon>) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.PokemonList.route) {
        composable(Routes.PokemonList.route) {
            MyApp(pokemons) {
                navController.navigate("${Routes.PokemonDetails.route}/$it")
            }
        }

        composable(Routes.PokemonDetails.routeWithArgument,
            arguments = listOf(navArgument(Routes.PokemonDetails.argument) {
                type = NavType.IntType
            })
        ) {
            navBackStackEntry ->
            val position = navBackStackEntry.arguments?.getInt(Routes.PokemonDetails.argument,1) as Int
            val pokemon: Pokemon
            //Pokemon(pokemon)

        }

    }
}

sealed class Routes(val route: String) {
    object PokemonList : Routes("PokemonList")
    object PokemonDetails : Routes("PokemonDetails") {
        const val routeWithArgument = "PokemonDetails/{position}"
        const val argument = "position"
    }
}