package com.example.cinemana.presentation.home.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemana.movie.domain.repository.MovieRepository
import com.example.jetmovie.utils.collectAndHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    init {
        fetchDiscoverMovies()
        fetchTrendingMovies()
    }
    fun fetchDiscoverMovies(){
        viewModelScope.launch {
            repository.fetchDiscoverMovies().collectAndHandle(
                onError = { error ->
                    _homeState.update {
                        it.copy(isLoading = false, error = error?.message)
                    }
                },
                onLoading = {
                    _homeState.update {
                        it.copy(isLoading = true)
                    }
                }
            ){ movies ->
                _homeState.update {
                    it.copy(
                        discoverMovies = movies,
                        isLoading = false,
                        error = null
                    )
                }
            }
        }
    }

    fun fetchTrendingMovies(){
        viewModelScope.launch {
            repository.fetchTrendingMovies().collectAndHandle(
                onError = { error ->
                    _homeState.update {
                        it.copy(isLoading = false, error = error?.message)
                    }
                },
                onLoading = {
                    _homeState.update {
                        it.copy(isLoading = true)
                    }
                }
            ){ movies ->
                _homeState.update {
                    it.copy(
                        trendingMovies = movies,
                        isLoading = false,
                        error = null
                    )
                }
            }
        }
    }
}