package com.example.movieFinal.presentation.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieFinal.presentation.home.component.AutoScrollingMoviePager
import com.example.movieFinal.presentation.home.component.CategoriesSection
import com.example.movieFinal.presentation.home.component.CustomSearchBar
import com.example.movieFinal.presentation.home.component.MovieSection

val categories = listOf(
    "Action",
    "Adventure",
    "Animation",
    "Comedy",
    "Crime",
    "Documentary",
    "Drama",
    "Family",
    "Fantasy",
    "History",
    "Horror",
    "Music",
    "Mystery",
    "Romance",
    "Science Fiction",
    "TV Movie",
    "Thriller",
    "War",
    "Western"
)

@Composable
fun HomeScreen(
    homeState: HomeState,
    modifier: Modifier = Modifier,
    onMovieClick: (id: Int) -> Unit,
    onEvent: (HomeScreenEvent) -> Unit
) {
    var selectedCategory by remember { mutableStateOf(categories.first()) }
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        CustomSearchBar(
            value = "",
            onValueChange = {},
            onFilterClick = {}
        )
        Spacer(modifier = Modifier.height(30.dp))

        AutoScrollingMoviePager(movies = homeState.trendingMovies, onItemClick = { onMovieClick(it) })
        Spacer(modifier = Modifier.height(12.dp))
        CategoriesSection(
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = {
                selectedCategory = it
                onEvent(HomeScreenEvent.OnCategorySelected(it))
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        MovieSection(
            movies = homeState.discoverMovies,
            onMovieClick = { onMovieClick(it) }
        )
    }
}
