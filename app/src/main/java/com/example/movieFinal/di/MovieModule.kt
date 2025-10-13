package com.example.movieFinal.di

import com.example.movieFinal.common.ApiMapper
import com.example.movieFinal.movie.data.mapper_impl.ApiMapperImpl
import com.example.movieFinal.movie.data.remote.model.MovieDto
import com.example.movieFinal.movie.data.remote.model.api.ApiService
import com.example.movieFinal.movie.data.repository.MovieRepositoryImpl
import com.example.movieFinal.movie.domain.model.Movie
import com.example.movieFinal.movie.domain.repository.MovieRepository
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
import kotlin.jvm.java


@Module
@InstallIn(SingletonComponent::class)
object MovieModule {


    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideMovieApi(): ApiService {
        val contentType = "application/json".toMediaType()
        val retrofit = Retrofit.Builder()
            .baseUrl(K.BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()

        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMapper(): ApiMapper<List<Movie>, MovieDto> {
        return ApiMapperImpl()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        mapper: ApiMapper<List<Movie>, MovieDto>,
        apiService: ApiService
    ): MovieRepository {
        return MovieRepositoryImpl(api = apiService, mapper = mapper)
    }
}