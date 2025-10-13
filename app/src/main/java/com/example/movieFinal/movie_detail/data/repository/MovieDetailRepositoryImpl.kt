package com.example.movieFinal.movie_detail.data.repository

import com.example.movieFinal.movie_detail.data.remote.models.MovieDetailDto
import com.example.jetmovie.utils.Response
import com.example.movieFinal.common.ApiMapper
import com.example.movieFinal.movie_detail.data.remote.api.MovieDetailApiService
import com.example.movieFinal.movie_detail.domain.models.MovieDetail
import com.example.movieFinal.movie_detail.domain.repository.MovieDetailRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MovieDetailRepositoryImpl @Inject constructor(
    private val mapper: ApiMapper<MovieDetail, MovieDetailDto>,
    private val apiService: MovieDetailApiService
) : MovieDetailRepository {

    override fun fetchMovieDetail(movieId: Int): Flow<Response<MovieDetail>> = flow {
        emit(Response.Loading())
        val movieDetailDto = apiService.fetchMovieDetail(movieId)
        mapper.mapToDomain(movieDetailDto).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        e.printStackTrace()
        emit(Response.Error(e))
    }

}