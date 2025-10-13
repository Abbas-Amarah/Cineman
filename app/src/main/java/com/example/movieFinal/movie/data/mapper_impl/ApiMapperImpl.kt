package com.example.movieFinal.movie.data.mapper_impl

import android.util.Log
import com.example.movieFinal.common.ApiMapper
import com.example.movieFinal.movie.data.remote.model.MovieDto
import com.example.movieFinal.movie.domain.model.Movie
import com.example.movieFinal.utils.GenreConstants

class ApiMapperImpl: ApiMapper<List<Movie>, MovieDto>{
    override fun mapToDomain(apiDto: MovieDto): List<Movie> {
        return apiDto.results?.map { result ->
            Log.d("TAG", "Poster Path: ${result?.posterPath}")
            Log.d("TAG", "Backdrop Path: ${result?.backdropPath}")
            Movie(
                backdropPath = formatEmptyValue(result?.backdropPath),
                posterPath = formatEmptyValue(result?.posterPath),
                genreIds = formatGenre(result?.genreIds),
                id = result?.id ?: 0,
                originalLanguage = formatEmptyValue(result?.originalLanguage, "language"),
                originalTitle = formatEmptyValue(result?.originalTitle, "title"),
                overview = formatEmptyValue(result?.overview, "overview"),
                popularity = result?.popularity ?: 0.0,
                releaseDate = formatEmptyValue(result?.releaseDate, "date"),
                title = formatEmptyValue(result?.title, "title"),
                voteAverage = result?.voteAverage ?: 0.0,
                voteCount = result?.voteCount ?: 0,
                video = result?.video ?: false,
                adult = result?.adult ?: false
            )
        } ?: emptyList()
    }
}


private fun formatEmptyValue(value: String?, default: String = ""): String {
    if (value.isNullOrEmpty()) return "Unknown $default"
    return value
}

private fun formatGenre(genreIds: List<Int?>?): List<String> {
    return genreIds?.map { GenreConstants.getGenreNameById(it ?: 0) } ?: emptyList()
}