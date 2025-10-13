package com.example.movieFinal.presentation.detail.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieFinal.movie_detail.domain.repository.MovieDetailRepository
import com.example.movieFinal.utils.K
import com.example.movieFinal.utils.collectAndHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val repository: MovieDetailRepository,
//    private val savedStateHandle: SavedStateHandle
): ViewModel() {


    private val _detailState = MutableStateFlow(DetailState())
    val detailState = _detailState.asStateFlow()

//    init {
//        fetchMovieDetailById()
//    }
//
//    val id: Int = savedStateHandle.get<Int>(K.MOVIE_ID) ?: -1

    fun fetchMovieDetailById(id: Int) = viewModelScope.launch {
        if (id == -1) {
            _detailState.update {
                it.copy(isLoading = false, error = "Movie not found")
            }
        } else {
            repository.fetchMovieDetail(id).collectAndHandle(
                onError = { error ->
                    _detailState.update {
                        it.copy(isLoading = false, error = error?.message)
                    }
                },
                onLoading = {
                    _detailState.update {
                        it.copy(isLoading = true, error = null)
                    }
                }
            ) { movieDetail ->
                _detailState.update {
                    it.copy(
                        isLoading = false,
                        error = null,
                        movie = movieDetail
                    )
                }
            }
        }
    }
}