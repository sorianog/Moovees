package com.sorianog.moovees.data.source

import com.sorianog.moovees.data.api.TMDBApiService
import com.sorianog.moovees.data.entity.MoviesResponse
import retrofit2.Response
import javax.inject.Inject


class MovieDataSourceImpl @Inject constructor(
    private val apiService: TMDBApiService
) : MovieDataSource {
    override suspend fun getMovies(): Response<MoviesResponse> {
        return apiService.getMovies()
    }
}