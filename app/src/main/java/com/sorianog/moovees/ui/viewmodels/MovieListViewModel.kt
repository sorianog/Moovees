package com.sorianog.moovees.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sorianog.moovees.data.MovieRepository
import com.sorianog.moovees.data.api.DataState
import com.sorianog.moovees.data.entity.MovieModelLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _movieState: MutableStateFlow<DataState<List<MovieModelLocal>>> =
        MutableStateFlow(DataState.Loading())
    val movieState: StateFlow<DataState<List<MovieModelLocal>>> = _movieState

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