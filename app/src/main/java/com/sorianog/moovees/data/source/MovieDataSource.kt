package com.sorianog.moovees.data.source

import com.sorianog.moovees.data.entity.MovieDetailModel
import com.sorianog.moovees.data.entity.MoviesResponse
import retrofit2.Response

interface MovieDataSource {
    suspend fun getMovies(): Response<MoviesResponse>
    suspend fun getMovie(movieId: Int): Response<MovieDetailModel>
}