package com.example.cinemana.movie.domain.repository

import com.example.cinemana.movie.domain.model.Movie
import com.example.jetmovie.utils.Response
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun fetchDiscoverMovies(): Flow<Response<List<Movie>>>
    suspend fun fetchTrendingMovies(): Flow<Response<List<Movie>>>
}