package com.sorianog.moovees.data.source

import com.sorianog.moovees.data.entity.MovieModelLocal

interface MovieDataSourceLocal {
    suspend fun getMovies(): List<MovieModelLocal>
    suspend fun getMovie(movieId: Int): MovieModelLocal
    suspend fun insertMovies(movies: List<MovieModelLocal>)
    suspend fun updateMovie(movie: MovieModelLocal)
    suspend fun deleteAllMovies()
}