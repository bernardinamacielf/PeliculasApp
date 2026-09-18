package com.example.peliculasapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peliculasapp.data.Movie
import com.example.peliculasapp.data.MovieRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.peliculasapp.data.MovieDetails

class MovieViewModel : ViewModel() {

    private val repository = MovieRepository()

    var movies by mutableStateOf<List<Movie>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    var selectedMovie by mutableStateOf<MovieDetails?>(null)
        private set

    var selectedMovieId by mutableStateOf<Int?>(null)
        private set

    fun loadMovies() {
        viewModelScope.launch {
            isLoading = true
            error = null

            try {
                movies = repository.getPopularMovies()
            } catch (e: Exception) {
                error = "No se pudieron cargar las películas"
            }

            isLoading = false
        }
    }

    fun loadMovieDetails(movieId: Int) {
        selectedMovieId = movieId

        viewModelScope.launch {
            isLoading = true
            error = null

            try {
                selectedMovie = repository.getMovieDetails(movieId)
            } catch (e: Exception) {
                error = "No se pudieron cargar los detalles de la película"
            }

            isLoading = false
        }
    }

    fun clearSelectedMovie() {
        selectedMovie = null
        error = null
    }
}