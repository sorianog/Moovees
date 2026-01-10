package com.sorianog.moovees.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.sorianog.moovees.data.api.ApiState
import com.sorianog.moovees.ui.components.MovieList
import com.sorianog.moovees.ui.viewmodels.MovieListViewModel

@Composable
fun MovieListScreen(
    movieListViewModel: MovieListViewModel = hiltViewModel()
) {
    val movieDataState by movieListViewModel.movieState.collectAsState()

    when (movieDataState) {
        is ApiState.Loading<*> -> {
            // TODO: Display loading component
        }

        is ApiState.Success<*> -> {
            val movieData = (movieDataState as ApiState.Success).data
            if (movieData.results.isNotEmpty()) {
                MovieList(movieData.results)
            } else {
                // TODO: Display empty message
            }
        }

        is ApiState.Error<*> -> {
            // TODO: Display error component
            val error = (movieDataState as ApiState.Error).error
            println("### error: $error")
        }
    }
}