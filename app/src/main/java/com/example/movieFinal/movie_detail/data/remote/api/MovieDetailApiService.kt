package com.example.cinemana.movie_detail.data.remote.api


import com.example.cinemana.BuildConfig
import com.example.cinemana.movie.data.remote.model.MovieDto
import com.example.cinemana.movie_detail.data.remote.models.MovieDetailDto
import com.example.cinemana.utils.K
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


private const val MOVIE_ID = "movie_id"
interface MovieDetailApiService {

    @GET("${K.MOVIE_DETAIL_ENDPOINT}${MOVIE_ID}")
    suspend fun fetchMovieDetail(
        @Path(MOVIE_ID) movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("append_to_response") appendToResponse: String = "credits,reviews",
    ): MovieDetailDto

}