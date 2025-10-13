package com.example.movieFinal.presentation.detail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieFinal.movie_detail.domain.models.MovieDetail
import com.example.movieFinal.presentation.detail.component.ActorItem
import com.example.movieFinal.presentation.detail.component.SmallBadge
import com.example.movieFinal.utils.K


@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    movie: MovieDetail?,
    onOpenImdb: (MovieDetail) -> Unit = {},
    onToggleFavorite: (MovieDetail) -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme
    if (movie == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                // Backdrop area
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(380.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .shadow(6.dp, RoundedCornerShape(14.dp))
                ) {
                    AsyncImage(
                        model = ("${K.BASE_IMAGE_URL}${movie.backdropPath}"),
                        contentDescription = "backdrop",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    // gradient to make text readable
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        colors.surface.copy(alpha = 0.9f)
                                    ),
                                    startY = 160f
                                )
                            )
                    )

                    // centered play button using theme primary
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .align(Alignment.Center)
                            .clip(CircleShape)
                            .background(colors.primary)
                            .clickable { /* play trailer */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = "play",
                            tint = colors.onPrimary,
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    // top-left badges
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .align(Alignment.TopStart),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        SmallBadge(text = if (movie.adult) "+18" else "PG")

                        // show up to two genres from the model
                        movie.genreIds.take(2).forEach { g -> SmallBadge(text = g) }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(colors.surfaceVariant.copy(alpha = 0.6f))
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = "rating",
                                    tint = Color.Yellow,
                                    modifier = Modifier.size(14.dp)
                                )
                                Text(
                                    text = "%.1f".format(movie.voteAverage),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = colors.onSurface
                                )
                            }
                        }
                    }

                    // favorite heart top-right
                    var isFav by remember { mutableStateOf(false) }
                    IconButton(
                        onClick = {
                            isFav = !isFav
                            onToggleFavorite(movie)
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "favorite",
                            tint = if (isFav) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.9f
                            )
                        )
                    }

                    // small duration / badge bottom-right
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(12.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(colors.surfaceVariant.copy(alpha = 0.6f))
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "1h 44m",
                            color = colors.onSurface,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Title + small metadata row
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = colors.onBackground
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Overview with expand toggle
                var expanded by remember { mutableStateOf(false) }
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = movie.overview,
                        style = MaterialTheme.typography.bodyMedium,
                        color = colors.onBackground.copy(alpha = 0.9f),
                        maxLines = if (expanded) Int.MAX_VALUE else 3,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = if (expanded) "Show Less" else "Show More",
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .clickable { expanded = !expanded },
                        color = colors.primary,
                        style = MaterialTheme.typography.labelLarge
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Actors row
                Text(
                    text = "Actors",
                    style = MaterialTheme.typography.titleMedium,
                    color = colors.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(movie.cast) { actor ->
                        ActorItem(name = actor.name, imageUrl = actor.profilePath)
                    }
                }

                Spacer(modifier = Modifier.height(32gi.dp))

                // IMDb button styled with theme secondary (yellow)
                Button(
                    onClick = { onOpenImdb(movie) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.secondary,
                        contentColor = colors.onSecondary
                    )
                ) {
                    Text(
                        text = "Open IMDb",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }

        }
    }
}

//val sampleActors = listOf(
//    Pair("Bérénice Bejo", "https://randomuser.me/api/portraits/women/68.jpg"),
//    Pair("Nassim Lyes", "https://randomuser.me/api/portraits/men/52.jpg"),
//    Pair("Léa Léviant", "https://randomuser.me/api/portraits/women/44.jpg"),
//    Pair("Anne M", "https://randomuser.me/api/portraits/women/12.jpg")
//)

