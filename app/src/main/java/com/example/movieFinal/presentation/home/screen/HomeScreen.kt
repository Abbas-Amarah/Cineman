package com.example.cinemana.presentation.home.screen

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
import com.example.cinemana.categories
import com.example.cinemana.presentation.home.component.AutoScrollingMoviePager
import com.example.cinemana.presentation.home.component.CategoriesSection
import com.example.cinemana.presentation.home.component.CustomSearchBar
import com.example.cinemana.presentation.home.component.MovieSection


@Composable
fun HomeScreen(
    homeState: HomeState,
    modifier: Modifier = Modifier
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

        AutoScrollingMoviePager(movies = homeState.trendingMovies, onItemClick = {})
        Spacer(modifier = Modifier.height(12.dp))
        CategoriesSection(
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it }
        )
        Spacer(modifier = Modifier.height(12.dp))
        MovieSection(
            movies = homeState.discoverMovies,
            onMovieClick = {}
        )
    }
}
