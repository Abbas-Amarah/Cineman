package com.example.cinemana.di

import com.example.cinemana.common.ApiMapper
import com.example.cinemana.movie.data.mapper_impl.ApiMapperImpl
import com.example.cinemana.movie.data.remote.model.MovieDto
import com.example.cinemana.movie.data.remote.model.api.ApiService
import com.example.cinemana.movie.data.repository.MovieRepositoryImpl
import com.example.cinemana.movie.domain.model.Movie
import com.example.cinemana.movie.domain.repository.MovieRepository
import com.example.cinemana.utils.K
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieModule {


    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideMovieApi(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(K.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
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