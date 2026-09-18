package com.example.peliculasapp.data

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface TmdbApiService {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Header("Authorization") authorization: String
    ): MovieResponse

    @GET("3/movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Header("Authorization") authorization: String
    ): MovieDetails
}