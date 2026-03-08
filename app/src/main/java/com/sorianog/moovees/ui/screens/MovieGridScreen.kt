package com.sorianog.moovees.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.sorianog.moovees.R
import com.sorianog.moovees.data.api.DataState
import com.sorianog.moovees.ui.components.EmptyStateUI
import com.sorianog.moovees.ui.components.LoadingIndicator
import com.sorianog.moovees.ui.components.MovieGrid
import com.sorianog.moovees.ui.viewmodels.MovieListViewModel

@Composable
fun MovieGridScreen(
    movieListViewModel: MovieListViewModel = hiltViewModel()
) {
    val movieDataState by movieListViewModel.movieState.collectAsState()

    when (movieDataState) {
        is DataState.Loading<*> -> {
            LoadingIndicator()
        }

        is DataState.Success<*> -> {
            val movieData = (movieDataState as DataState.Success).data
            if (movieData.isNotEmpty()) {
                MovieGrid(movieData)
            } else {
                EmptyStateUI(
                    image = painterResource(R.drawable.ic_info),
                    message = stringResource(R.string.no_movies)
                )
            }
        }

        is DataState.Error<*> -> {
            val error = (movieDataState as DataState.Error).error
            EmptyStateUI(
                image = painterResource(R.drawable.ic_error),
                message = error.toString()
            )
        }
    }
}