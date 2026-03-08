package com.sorianog.moovees.data.source

import com.sorianog.moovees.data.api.TMDBApiService
import com.sorianog.moovees.data.entity.MovieDetailModel
import com.sorianog.moovees.data.entity.MoviesResponse
import retrofit2.Response
import javax.inject.Inject


class MovieDataSourceRemoteImpl @Inject constructor(
    private val apiService: TMDBApiService
) : MovieDataSourceRemote {
    override suspend fun getMovies(): Response<MoviesResponse> {
        return apiService.getMovies()
    }
    override suspend fun getMovie(movieId: Int): Response<MovieDetailModel> {
        return apiService.getMovie(movieId)
    }
}