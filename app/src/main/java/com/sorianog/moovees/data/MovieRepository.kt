package com.sorianog.moovees.data

import com.sorianog.moovees.data.api.ApiState
import com.sorianog.moovees.data.entity.MoviesResponse
import com.sorianog.moovees.data.source.MovieDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDataSource: MovieDataSource
) {
    fun getMovies(): Flow<ApiState<MoviesResponse>> {

        return flow {
            emit(ApiState.Loading())

            val response = movieDataSource.getMovies()

            if (response.isSuccessful && response.body() != null) {
                emit(ApiState.Success(response.body()!!))
            } else {
                emit(ApiState.Error("Error fetching movies: ${response.code()}"))
            }
        }.catch { err ->
            emit(ApiState.Error(err.localizedMessage ?: "Error in movie flow occurred"))
        }
    }
}