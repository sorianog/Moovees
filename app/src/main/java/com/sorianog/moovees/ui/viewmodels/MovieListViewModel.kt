package com.sorianog.moovees.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sorianog.moovees.data.MovieRepository
import com.sorianog.moovees.data.api.ApiState
import com.sorianog.moovees.data.entity.MoviesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject


@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movieState: MutableStateFlow<ApiState<MoviesResponse>> =
        MutableStateFlow(ApiState.Loading())
    val movieState: StateFlow<ApiState<MoviesResponse>> = _movieState

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.getMovies().collectLatest { movieResp ->
                _movieState.update { movieResp }
            }
        }
    }
}