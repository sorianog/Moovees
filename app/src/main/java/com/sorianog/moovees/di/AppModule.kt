package com.sorianog.moovees.di

import android.R.attr.apiKey
import com.sorianog.moovees.BuildConfig
import com.sorianog.moovees.data.api.ApiConstants
import com.sorianog.moovees.data.api.TMDBApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

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
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
            }.asConverterFactory("application/json".toMediaType()))

            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): TMDBApiService {
        return retrofit.create(TMDBApiService::class.java)
    }
}