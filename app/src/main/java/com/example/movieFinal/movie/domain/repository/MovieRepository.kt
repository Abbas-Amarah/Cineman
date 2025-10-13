package com.example.movieFinal.movie.domain.repository

import com.example.movieFinal.movie.domain.model.Movie
import com.example.jetmovie.utils.Response
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun fetchDiscoverMovies(category: String): Flow<Response<List<Movie>>>
    suspend fun fetchTrendingMovies(): Flow<Response<List<Movie>>>
}