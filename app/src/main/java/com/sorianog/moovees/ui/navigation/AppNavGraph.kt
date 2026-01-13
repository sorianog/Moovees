package com.sorianog.moovees.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sorianog.moovees.ui.screens.MovieDetailScreen
import com.sorianog.moovees.ui.screens.MovieGridScreen
import com.sorianog.moovees.ui.screens.MovieListScreen

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = AppRoutes.LIST_SCREEN) {

        composable(AppRoutes.LIST_SCREEN) {
            MovieListScreen(
                onMovieClick = { movieId ->
                    navController.navigateToMovieDetail(movieId)
                }
            )
        }

        composable(AppRoutes.GRID_SCREEN) {
            MovieGridScreen()
        }

        composable(
            route = MovieDetailNav.routeWithArgs,
            arguments = MovieDetailNav.arguments
        ) { navBackStackEntry ->
            val movieId = navBackStackEntry.arguments?.getInt(MovieDetailNav.movieIdArg)
            MovieDetailScreen(movieId)
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }

private fun NavHostController.navigateToMovieDetail(movieId: Int) {
    this.navigateSingleTopTo("${MovieDetailNav.route}/$movieId")
}