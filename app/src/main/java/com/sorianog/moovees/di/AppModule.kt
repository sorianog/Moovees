package com.sorianog.moovees.di

import com.sorianog.moovees.BuildConfig
import com.sorianog.moovees.data.MovieRepository
import com.sorianog.moovees.data.api.ApiConstants
import com.sorianog.moovees.data.api.TMDBApiService
import com.sorianog.moovees.data.local.MovieDao
import com.sorianog.moovees.data.source.MovieDataSourceLocal
import com.sorianog.moovees.data.source.MovieDataSourceLocalImpl
import com.sorianog.moovees.data.source.MovieDataSourceRemote
import com.sorianog.moovees.data.source.MovieDataSourceRemoteImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(
                object : Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val request: Request = chain.request()
                            .newBuilder()
                            .header("accept", "application/json")
                            .header("Authorization", BuildConfig.TMDB_API_KEY)
                            .build()
                        return chain.proceed(request)
                    }
                })

        return Retrofit.Builder()
            .baseUrl(ApiConstants.API_BASE_URL)
            .client(okHttpClient.build())
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): TMDBApiService {
        return retrofit.create(TMDBApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesMovieDataSource(apiService: TMDBApiService): MovieDataSourceRemote {
        return MovieDataSourceRemoteImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesMovieDataSourceLocal(movieDao: MovieDao): MovieDataSourceLocal {
        return MovieDataSourceLocalImpl(movieDao)
    }

    @Provides
    @Singleton
    fun providesMovieRepository(
        movieDataSourceRemote: MovieDataSourceRemote,
        movieDataSourceLocal: MovieDataSourceLocal
    ): MovieRepository {
        return MovieRepository(movieDataSourceRemote, movieDataSourceLocal)
    }
}