package com.example.movieFinal.movie.data.repository

import com.example.movieFinal.common.ApiMapper
import com.example.movieFinal.movie.data.remote.model.MovieDto
import com.example.movieFinal.movie.data.remote.model.api.ApiService
import com.example.movieFinal.movie.domain.model.Movie
import com.example.movieFinal.movie.domain.repository.MovieRepository
import com.example.jetmovie.utils.Response
import com.example.movieFinal.utils.GenreConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api : ApiService,
    private val mapper: ApiMapper<List<Movie>, MovieDto>
) : MovieRepository {
    override suspend fun fetchDiscoverMovies(category: String): Flow<Response<List<Movie>>> = flow {
        val category = GenreConstants.getGenreIdByName(category)
        emit(Response.Loading())
        val apiResponse = api.fetchDiscoverMovies(genre = category)
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