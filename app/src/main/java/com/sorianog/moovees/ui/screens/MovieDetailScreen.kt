package com.sorianog.moovees.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sorianog.moovees.R
import com.sorianog.moovees.data.api.ApiState
import com.sorianog.moovees.ui.components.EmptyStateUI
import com.sorianog.moovees.ui.components.LoadingIndicator
import com.sorianog.moovees.ui.components.MovieDetail
import com.sorianog.moovees.ui.viewmodels.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    movieId: Int?,
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel(),
) {
    val movieDetailState by movieDetailViewModel.movieDetailState.collectAsStateWithLifecycle()

    movieId?.let {
        LaunchedEffect(key1 = movieDetailViewModel) {
            movieDetailViewModel.getMovieDetail(it)
        }
    }

    when (movieDetailState) {
        is ApiState.Loading<*> -> {
            LoadingIndicator()
        }

        is ApiState.Success<*> -> {
            val movieDetail = (movieDetailState as ApiState.Success).data
            MovieDetail(movieDetail)
        }

        is ApiState.Error<*> -> {
            val error = (movieDetailState as ApiState.Error).error
            EmptyStateUI(
                image = painterResource(R.drawable.ic_error),
                message = error.toString()
            )
        }
    }
}