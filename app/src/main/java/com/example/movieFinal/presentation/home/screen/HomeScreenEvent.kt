package com.example.movieFinal.presentation.home.screen

sealed class HomeScreenEvent {
    data class OnCategorySelected(val category: String): HomeScreenEvent()
}
