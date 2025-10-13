package com.example.movieFinal.presentation.detail.screen

import com.example.movieFinal.movie_detail.domain.models.MovieDetail

data class DetailState(
    val movie: MovieDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isMovieLoading: Boolean = false
)