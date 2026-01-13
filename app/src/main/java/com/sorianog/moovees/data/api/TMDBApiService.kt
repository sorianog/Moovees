package com.sorianog.moovees.data.api

import com.sorianog.moovees.data.entity.MovieDetailModel
import com.sorianog.moovees.data.entity.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Docs: https://developer.themoviedb.org/
interface TMDBApiService {

    // Ref: https://developer.themoviedb.org/reference/discover-movie
    @GET("discover/movie?sort_by=popularity.desc&with_release_type=2|3")
    suspend fun getMovies(
        @Query("release_date.gte") minReleaseDate: String = "2026-01-01",
        @Query("release_date.lte") maxReleaseDate: String = "2031-01-01"
    ) : Response<MoviesResponse>

    // Ref: https://developer.themoviedb.org/reference/movie-details
    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") movieId: Int
    ) : Response<MovieDetailModel>
}