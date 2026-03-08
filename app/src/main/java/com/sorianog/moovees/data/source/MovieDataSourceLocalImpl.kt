package com.sorianog.moovees.data.source

import com.sorianog.moovees.data.entity.MovieModelLocal
import com.sorianog.moovees.data.local.MovieDao
import javax.inject.Inject

class MovieDataSourceLocalImpl @Inject constructor(
    private val movieDao: MovieDao
) : MovieDataSourceLocal {
    override suspend fun getMovies(): List<MovieModelLocal> {
        return movieDao.getMovies()
    }

    override suspend fun getMovie(movieId: Int): MovieModelLocal {
        return movieDao.getMovie(movieId)
    }

    override suspend fun insertMovies(movies: List<MovieModelLocal>) {
        movieDao.insertAll(movies)
    }

    override suspend fun updateMovie(movie: MovieModelLocal) {
        movieDao.updateMovie(movie)
    }

    override suspend fun deleteAllMovies() {
        movieDao.deleteAllMovies()
    }
}