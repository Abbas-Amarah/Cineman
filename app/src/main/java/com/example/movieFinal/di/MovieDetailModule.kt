package com.example.movieFinal.di

import com.example.movieFinal.movie_detail.data.mapper_impl.MovieDetailMapperImpl
import com.example.movieFinal.movie_detail.data.remote.models.MovieDetailDto
import com.example.movieFinal.common.ApiMapper
import com.example.movieFinal.movie_detail.data.remote.api.MovieDetailApiService
import com.example.movieFinal.movie_detail.data.repository.MovieDetailRepositoryImpl
import com.example.movieFinal.movie_detail.domain.models.MovieDetail
import com.example.movieFinal.movie_detail.domain.repository.MovieDetailRepository
import com.example.movieFinal.utils.K
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MovieDetailModule {

    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideMovieApi(): MovieDetailApiService {
        val contentType = "application/json".toMediaType()
        val retrofit = Retrofit.Builder()
            .baseUrl(K.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()

        return retrofit.create(MovieDetailApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDetailRepository(
        apiService: MovieDetailApiService,
        mapper: ApiMapper<MovieDetail, MovieDetailDto>
    ): MovieDetailRepository {
        return MovieDetailRepositoryImpl(
            mapper = mapper,
            apiService = apiService
        )
    }

    @Provides
    @Singleton
    fun provideMapper(): ApiMapper<MovieDetail, MovieDetailDto> {
        return MovieDetailMapperImpl()
    }
}