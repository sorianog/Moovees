package com.sorianog.moovees.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sorianog.moovees.data.MovieRepository
import com.sorianog.moovees.data.api.ApiState
import com.sorianog.moovees.data.entity.MovieDetailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _movieDetailState: MutableStateFlow<ApiState<MovieDetailModel>> =
        MutableStateFlow(ApiState.Loading())
    val movieDetailState: StateFlow<ApiState<MovieDetailModel>> = _movieDetailState

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepository.getMovie(movieId).collectLatest { movieDetail ->
                _movieDetailState.update { movieDetail }
            }
        }
    }
}