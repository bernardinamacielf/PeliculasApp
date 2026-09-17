package com.example.peliculasapp.data

import retrofit2.http.GET
import retrofit2.http.Header

interface TmdbApiService {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Header("Authorization") authorization: String
    ): MovieResponse
}