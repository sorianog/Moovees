package com.sorianog.moovees.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.BorderAll
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.sorianog.moovees.R
import com.sorianog.moovees.ui.navigation.AppRoutes.GRID_SCREEN
import com.sorianog.moovees.ui.navigation.AppRoutes.LIST_SCREEN

object AppRoutes {
    const val LIST_SCREEN = "LIST"
    const val GRID_SCREEN = "GRID"
    const val DETAIL_SCREEN = "MOVIE_DETAIL"
}

enum class Destination(
    val route: String,
    @StringRes
    val label: Int,
    val icon: ImageVector,
    @StringRes
    val contentDesc: Int
) {
    LIST(LIST_SCREEN, R.string.list, Icons.AutoMirrored.Filled.List, R.string.list),
    GRID(GRID_SCREEN, R.string.grid, Icons.Default.BorderAll, R.string.grid)
}

object MovieDetailNav {
    val route = AppRoutes.DETAIL_SCREEN
    val movieIdArg = "movie_id"
    val routeWithArgs = "$route/{${movieIdArg}}"
    val arguments = listOf(
        navArgument(movieIdArg) { type = NavType.IntType }
    )
}