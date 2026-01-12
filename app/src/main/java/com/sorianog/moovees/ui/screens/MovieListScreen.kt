package com.sorianog.moovees.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.sorianog.moovees.R
import com.sorianog.moovees.data.api.ApiState
import com.sorianog.moovees.ui.components.EmptyStateUI
import com.sorianog.moovees.ui.components.LoadingIndicator
import com.sorianog.moovees.ui.components.MovieList
import com.sorianog.moovees.ui.viewmodels.MovieListViewModel

@Composable
fun MovieListScreen(
    movieListViewModel: MovieListViewModel = hiltViewModel()
) {
    val movieDataState by movieListViewModel.movieState.collectAsState()

    when (movieDataState) {
        is ApiState.Loading<*> -> {
            LoadingIndicator()
        }

        is ApiState.Success<*> -> {
            val movieData = (movieDataState as ApiState.Success).data
            if (movieData.results.isNotEmpty()) {
                MovieList(movieData.results)
            } else {
                EmptyStateUI(
                    image = painterResource(R.drawable.ic_info),
                    message = stringResource(R.string.no_movies)
                )
            }
        }

        is ApiState.Error<*> -> {
            val error = (movieDataState as ApiState.Error).error
            EmptyStateUI(
                image = painterResource(R.drawable.ic_error),
                message = error.toString()
            )
        }
    }
}