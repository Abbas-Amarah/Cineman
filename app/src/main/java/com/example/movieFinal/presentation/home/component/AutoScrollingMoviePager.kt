package com.example.movieFinal.presentation.home.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.movieFinal.movie.domain.model.Movie
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val AUTO_SCROLL_DELAY = 4000L

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoScrollingMoviePager(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    onItemClick: (Int) -> Unit
) {
    if (movies.isEmpty()) return

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        movies.size
    }

    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    // This is the corrected auto-scroll effect
    LaunchedEffect(key1 = isDragged) {
        if (!isDragged) {
            launch {
                while (true) {
                    delay(AUTO_SCROLL_DELAY)
                    val nextPage = (pagerState.currentPage + 1) % movies.size
                    pagerState.animateScrollToPage(nextPage)
                }
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp) // Maintain a fixed height for the pager area
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 40.dp),
            pageSpacing = 16.dp,
            verticalAlignment = Alignment.CenterVertically
        ) { page ->
            // In a real app, you would get the movie from your ViewModel based on the page index
            // For this component, we'll assume the list is passed in directly.
            val movie = movies[page]
            MovieItem(
                movie = movie,
                // Passing a simplified genre string for preview purposes.
                // In a real scenario, you'd resolve this from genre IDs.
                genres = "Drama, Horror, Mystery & Thriller",
                onItemClick = { onItemClick(movie.id) }
            )
        }
    }
}
