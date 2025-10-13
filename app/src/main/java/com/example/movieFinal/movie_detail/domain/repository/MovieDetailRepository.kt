package com.example.cinemana.movie_detail.domain.repository

import com.example.cinemana.movie.domain.model.Movie
import com.example.cinemana.movie_detail.data.remote.models.MovieDetailDto
import com.example.cinemana.movie_detail.domain.models.MovieDetail
import com.example.jetmovie.utils.Response
import kotlinx.coroutines.flow.Flow

interface MovieDetailRepository {

    fun fetchMovieDetail(movieId: Int): Flow<Response<MovieDetail>> }