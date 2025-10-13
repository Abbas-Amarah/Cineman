package com.example.cinemana.di

import androidx.compose.ui.input.key.Key
import com.example.cinemana.common.ApiMapper
import com.example.cinemana.movie.data.remote.model.api.ApiService
import com.example.cinemana.movie.data.remote.repository.MovieDetailRepositoryImpl
import com.example.cinemana.movie_detail.data.mapper_impl.MovieDetailMapperImpl
import com.example.cinemana.movie_detail.data.remote.api.MovieDetailApiService
import com.example.cinemana.movie_detail.data.remote.models.MovieDetailDto
import com.example.cinemana.movie_detail.domain.models.MovieDetail
import com.example.cinemana.movie_detail.domain.repository.MovieDetailRepository
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
object MovieDetailModule {

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