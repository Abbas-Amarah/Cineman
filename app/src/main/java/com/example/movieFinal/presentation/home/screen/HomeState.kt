package com.example.movieFinal.presentation.home.screen

import com.example.movieFinal.movie.domain.model.Movie

data class HomeState(
    val isLoading: Boolean = false,
    val discoverMovies: List<Movie> = emptyList(),
    val trendingMovies: List<Movie> = emptyList(),
    val error: String? = null,
    val selectedCategory: String = "Action"
)
