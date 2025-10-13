package com.example.cinemana.presentation.home.screen

import com.example.cinemana.movie.domain.model.Movie

data class HomeState(
    val isLoading: Boolean = false,
    val discoverMovies: List<Movie> = emptyList(),
    val trendingMovies: List<Movie> = emptyList(),
    val error: String? = null
)
