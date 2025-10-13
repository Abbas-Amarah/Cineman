package com.example.cinemana.movie.data.repository

import android.util.Log
import coil.util.CoilUtils.result
import com.example.cinemana.common.ApiMapper
import com.example.cinemana.movie.data.remote.model.MovieDto
import com.example.cinemana.movie.data.remote.model.api.ApiService
import com.example.cinemana.movie.domain.model.Movie
import com.example.cinemana.movie.domain.repository.MovieRepository
import com.example.jetmovie.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api : ApiService,
    private val mapper: ApiMapper<List<Movie>, MovieDto>
) : MovieRepository {
    override suspend fun fetchDiscoverMovies(): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val apiResponse = api.fetchDiscoverMovies()
        mapper.mapToDomain(apiResponse).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        emit(Response.Error(e))
    }

    override suspend fun fetchTrendingMovies(): Flow<Response<List<Movie>>> = flow {
        emit(Response.Loading())
        val apiResponse = api.fetchTrendingMovies()
        mapper.mapToDomain(apiResponse).apply {
            emit(Response.Success(this))
        }
    }.catch { e ->
        emit(Response.Error(e))
    }
}