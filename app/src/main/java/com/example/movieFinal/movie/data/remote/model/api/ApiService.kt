package com.example.movieFinal.movie.data.remote.model.api

import com.example.movieFinal.movie.data.remote.model.MovieDto
import com.example.movieFinal.utils.K
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(K.DISCOVER_MOVIE_ENDPOINT)
    suspend fun fetchDiscoverMovies(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("with_genres") genre: Int,
        @Query("api_key") apiKey: String = K.API_KEY
    ): MovieDto


    @GET(K.TRENDING_MOVIE_ENDPOINT)
    suspend fun fetchTrendingMovies(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("api_key") apiKey: String = K.API_KEY
    ): MovieDto

}