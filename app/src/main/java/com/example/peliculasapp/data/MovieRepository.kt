package com.example.peliculasapp.data

import com.example.peliculasapp.BuildConfig

class MovieRepository {

    private val api = RetrofitInstance.api

    suspend fun getPopularMovies(): List<Movie> {
        val response = api.getPopularMovies(
            authorization = "Bearer ${BuildConfig.TMDB_TOKEN}"
        )
        return response.results
    }
}