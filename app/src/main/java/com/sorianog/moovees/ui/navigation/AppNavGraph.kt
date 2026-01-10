package com.sorianog.moovees.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sorianog.moovees.ui.screens.MovieDetailScreen
import com.sorianog.moovees.ui.screens.MovieListScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppRoutes.LIST_SCREEN) {

        composable(AppRoutes.LIST_SCREEN) {
            MovieListScreen()
        }

        composable(AppRoutes.DETAIL_SCREEN) {
            MovieDetailScreen()
        }
    }
}