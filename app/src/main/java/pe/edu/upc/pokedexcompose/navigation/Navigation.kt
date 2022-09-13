package pe.edu.upc.pokedexcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pe.edu.upc.pokedexcompose.ui.pokemondetails.PokemonViewModel
import pe.edu.upc.pokedexcompose.ui.pokemonlist.MyApp
import pe.edu.upc.pokedexcompose.ui.pokemonlist.PokemonListViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import pe.edu.upc.pokedexcompose.ui.pokemondetails.PokemonDetails

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.PokemonList.route) {
        composable(Routes.PokemonList.route) {

            val pokemonListViewModel: PokemonListViewModel = viewModel()
            MyApp(pokemonListViewModel) {
                navController.navigate("${Routes.PokemonDetails.route}/$it")
            }
        }

        composable(
            Routes.PokemonDetails.routeWithArgument,
            arguments = listOf(navArgument(Routes.PokemonDetails.argument) {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val position =
                navBackStackEntry.arguments?.getInt(Routes.PokemonDetails.argument, 1) as Int
            val pokemonViewModel: PokemonViewModel = viewModel()
            pokemonViewModel.getPokemon(position)
            PokemonDetails(pokemonViewModel)

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