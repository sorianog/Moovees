package com.sorianog.moovees.data

import com.sorianog.moovees.data.api.DataState
import com.sorianog.moovees.data.entity.MovieModelLocal
import com.sorianog.moovees.data.entity.toLocal
import com.sorianog.moovees.data.source.MovieDataSourceLocal
import com.sorianog.moovees.data.source.MovieDataSourceRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDataSourceRemote: MovieDataSourceRemote,
    private val movieDataSourceLocal: MovieDataSourceLocal
) {
    fun getMovies(): Flow<DataState<List<MovieModelLocal>>> {
        return flow {
            emit(DataState.Loading())

            var movies = getMoviesLocal()
            if (movies.isEmpty()) {
                val response = movieDataSourceRemote.getMovies()

                if (response.isSuccessful) {
                    response.body()?.let { respBody ->
                        storeMovies(respBody.results.map { it.toLocal() })
                        movies = getMoviesLocal()

                        emit(DataState.Success(movies))
                    }
                } else {
                    emit(DataState.Error("Error fetching movies: ${response.code()}"))
                }
            } else {
                emit(DataState.Success(movies))
            }

        }.catch { err ->
            emit(DataState.Error(err.localizedMessage ?: "Error in movie flow occurred"))
        }
    }

    fun getMovie(movieId: Int): Flow<DataState<MovieModelLocal>> {

        return flow {
            emit(DataState.Loading())

            var movie = getMovieLocal(movieId)
            if (movie.runtime == null) {
                val response = movieDataSourceRemote.getMovie(movieId)
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let { movieDetail ->
                        movie = movie.copy(
                            runtime = movieDetail.runtime,
                            homepage = movieDetail.homepage
                        )
                        updateMovie(movie)
                        emit(DataState.Success(movie))
                    }
                } else {
                    emit(DataState.Error("Error fetching movie details: ${response.code()}"))
                }
            } else {
                emit(DataState.Success(movie))
            }
        }.catch { err ->
            emit(DataState.Error(err.localizedMessage ?: "Error in movie details flow occurred"))
        }
    }

    suspend fun storeMovies(movies: List<MovieModelLocal>) {
        movieDataSourceLocal.insertMovies(movies)
    }

    suspend fun getMoviesLocal(): List<MovieModelLocal> {
        return movieDataSourceLocal.getMovies()
    }

    suspend fun getMovieLocal(id: Int): MovieModelLocal {
        return movieDataSourceLocal.getMovie(id)
    }

    suspend fun updateMovie(movie: MovieModelLocal) {
        movieDataSourceLocal.updateMovie(movie)
    }
}