package com.example.cinemana.presentation.home.component

import android.R.attr.contentDescription
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cinemana.movie.domain.model.Movie
import com.example.cinemana.utils.K
import java.util.Locale

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    genres: String,
    onItemClick: (Int) -> Unit
) {
    val textShadow = Shadow(
        color = Color.Black.copy(alpha = 0.5f),
        offset = Offset(2f, 2f),
        blurRadius = 4f
    )

    Card(
        modifier = modifier
            .height(160.dp)
            .fillMaxWidth()
            .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("${K.BASE_IMAGE_URL}${movie.backdropPath}")
                    .crossfade(true)
                    .build(),
                contentDescription = "Movie backdrop",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.9f)
                            )
                        )
                    )
            )

            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color.Yellow,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = String.format("%.1f", movie.voteAverage),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        shadow = textShadow
                    )
                )
            }

            Surface(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(12.dp),
                color = MaterialTheme.colorScheme.error,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play Button",
                    tint = MaterialTheme.colorScheme.onError,
                    modifier = Modifier
                        .size(36.dp)
                        .padding(4.dp)
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        shadow = textShadow
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                val secondaryTextColor = Color.White.copy(alpha = 0.8f)
                Text(
                    text = genres,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = secondaryTextColor,
                        shadow = textShadow
                    )
                )
                Text(
                    text = movie.releaseDate,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = secondaryTextColor,
                        shadow = textShadow
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = Locale(movie.originalLanguage).displayLanguage,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = secondaryTextColor,
                        fontWeight = FontWeight.Medium,
                        shadow = textShadow
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieItemPreview() {
    val movie = Movie(
        backdropPath = "/h5Hksib4up3Oq2Wq12f2w4z4wQ.jpg",
        genreIds = emptyList(),
        id = 1,
        originalLanguage = "ar",
        originalTitle = "Rima",
        overview = "This is a movie about something interesting.",
        popularity = 123.45,
        posterPath = "/poster.jpg",
        releaseDate = "15, March, 2020",
        title = "Rima",
        video = false,
        voteAverage = 4.7,
        voteCount = 100
    )
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        MovieItem(
            modifier = Modifier.fillMaxWidth(),
            movie = movie,
            genres = "Drama, Horror, Mystery & Thriller",
            onItemClick = {})
    }
}
