package com.example.movieFinal.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.movieFinal.presentation.detail.screen.DetailScreenViewModel
import com.example.movieFinal.presentation.detail.screen.MovieDetailsScreen
import com.example.movieFinal.presentation.home.screen.HomeScreen
import com.example.movieFinal.presentation.home.screen.HomeViewModel
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Home : Screen()
    @Serializable
    data class Detail(val id: Int) : Screen()
}

@Composable
fun MovieNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home
    ) {
        composable<Screen.Home> {
            val viewModel = hiltViewModel<HomeViewModel>()
            val homeState by viewModel.homeState.collectAsStateWithLifecycle()

            HomeScreen(
                modifier = modifier,
                homeState = homeState,
                onMovieClick = { id ->
                    navController.navigate(Screen.Detail(id))
                },
                onEvent = viewModel::onEvent
            )
        }
        composable<Screen.Detail> { backStackEntry ->
            val args = backStackEntry.toRoute<Screen.Detail>()
            val viewModel = hiltViewModel<DetailScreenViewModel>()
            val detailState by viewModel.detailState.collectAsStateWithLifecycle()

            LaunchedEffect(args.id) {
                viewModel.fetchMovieDetailById(args.id)
            }

            MovieDetailsScreen(
                modifier = modifier,
                movie = detailState.movie,
                onOpenImdb = { },
                onToggleFavorite = { },
            )
        }
    }
}