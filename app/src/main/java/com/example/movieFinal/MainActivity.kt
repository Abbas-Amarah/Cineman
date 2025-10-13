package com.example.cinemana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cinemana.movie.domain.model.Movie
import com.example.cinemana.presentation.home.screen.HomeScreen
import com.example.cinemana.presentation.home.screen.HomeViewModel
import com.example.jetmovie.presentation.theme.DarkTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val viewModel = hiltViewModel<HomeViewModel>()
            val homsState by viewModel.homeState.collectAsStateWithLifecycle()

            DarkTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        homeState = homsState
                    )
                }
            }
        }
    }
}

val movies = listOf<Movie>(
    Movie(
        backdropPath = "https://image.tmdb.org/t/p/w500/xDMIl84Qo5Tsu62c9DGWhmPI67A.jpg",
        genreIds = listOf("Action", "Drama", "Romance"),
        id = 1,
        originalLanguage = "ar",
        originalTitle = "Rima",
        overview = "A romantic drama set in the Middle East exploring cultural traditions.",
        popularity = 123.45,
        posterPath = "/xDMIl84Qo5Tsu62c9DGWhmPI67A.jpg",
        releaseDate = "15, March, 2020",
        title = "Rima",
        video = false,
        voteAverage = 4.7,
        voteCount = 100
    ),
    Movie(
        backdropPath = "https://image.tmdb.org/t/p/w500/8ZTVqvKDQ8emSGUEMjsS4yHAwrp.jpg",
        genreIds = emptyList(),
        id = 2,
        originalLanguage = "en",
        originalTitle = "Inception",
        overview = "A thief who steals corporate secrets through dream-sharing technology.",
        popularity = 234.56,
        posterPath = "/oYuLEt3zVCKq57qu2F8dT7NIa6f.jpg",
        releaseDate = "16, July, 2010",
        title = "Inception",
        video = false,
        voteAverage = 8.3,
        voteCount = 30000
    ),
    Movie(
        backdropPath = "https://image.tmdb.org/t/p/w500/ruF3Lmd2hs4hX13e7q6BTT6bP2Z.jpg",
        genreIds = emptyList(),
        id = 3,
        originalLanguage = "en",
        originalTitle = "The Avengers",
        overview = "Earth's mightiest heroes must come together to stop Loki.",
        popularity = 345.67,
        posterPath = "/RYMX2wcKCBAr24UyPD7xwmjaTn.jpg",
        releaseDate = "4, May, 2012",
        title = "The Avengers",
        video = false,
        voteAverage = 8.0,
        voteCount = 28000
    ),
    Movie(
        backdropPath = "https://image.tmdb.org/t/p/w500/lmZFxXgJE3vgrciwuDib0N8CfQo.jpg",
        genreIds = emptyList(),
        id = 4,
        originalLanguage = "en",
        originalTitle = "Avengers: Infinity War",
        overview = "The Avengers and their allies must be willing to sacrifice all.",
        popularity = 456.78,
        posterPath = "/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
        releaseDate = "27, April, 2018",
        title = "Avengers: Infinity War",
        video = false,
        voteAverage = 8.4,
        voteCount = 25000
    ),
    Movie(
        backdropPath = "https://image.tmdb.org/t/p/w500/hqkIcbrOHL86UncnHIsHVcVmzue.jpg",
        genreIds = emptyList(),
        id = 5,
        originalLanguage = "en",
        originalTitle = "The Dark Knight",
        overview = "Batman faces the Joker, a criminal mastermind seeking to create chaos.",
        popularity = 567.89,
        posterPath = "/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
        releaseDate = "18, July, 2008",
        title = "The Dark Knight",
        video = false,
        voteAverage = 9.0,
        voteCount = 27000
    ),
    Movie(
        backdropPath = "https://image.tmdb.org/t/p/w500/pZvZjxPFfWWIwtPQodsNygQ6u5Z.jpg",
        genreIds = emptyList(),
        id = 6,
        originalLanguage = "en",
        originalTitle = "Interstellar",
        overview = "A team of explorers travel through a wormhole in space.",
        popularity = 678.90,
        posterPath = "/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg",
        releaseDate = "7, November, 2014",
        title = "Interstellar",
        video = false,
        voteAverage = 8.6,
        voteCount = 29000
    )
)
val categories = listOf("Action", "Drama", "Comedy", "Romance", "Horror")
