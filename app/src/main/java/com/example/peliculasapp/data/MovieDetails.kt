package com.example.peliculasapp.data

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    val id: Int,
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    val overview: String,
    val genres: List<Genre>,
    @SerializedName("vote_average")
    val voteAverage: Double
)

data class Genre(
    val id: Int,
    val name: String
)