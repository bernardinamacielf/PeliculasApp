package com.example.peliculasapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.peliculasapp.ui.theme.PeliculasAppTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import coil.compose.AsyncImage
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.peliculasapp.viewmodel.MovieViewModel
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import com.example.peliculasapp.data.MovieDetails

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PeliculasAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.loadMovies()
    }
    val movies = viewModel.movies
    val isLoading = viewModel.isLoading
    val error = viewModel.error
    val selectedMovie = viewModel.selectedMovie
    val selectedMovieId = viewModel.selectedMovieId
    if (selectedMovie != null) {
        MovieDetailsScreen(
            movie = selectedMovie,
            onBack = {
                viewModel.clearSelectedMovie()
            }
        )
    } else {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Text(
                text = "Películas populares",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                    bottom = 8.dp
                )
            )
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (error != null) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = error
                    )
                    Button(
                        onClick = {
                            selectedMovieId?.let {
                                viewModel.loadMovieDetails(it)
                            } ?: viewModel.loadMovies()
                        }
                    ) {
                        Text("Reintentar")
                    }
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(movies) { movie ->
                        Card(
                            onClick = {
                                viewModel.loadMovieDetails(movie.id)
                            },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            )
                        ) {
                            Column {
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                                    contentDescription = movie.title,
                                    modifier = Modifier.fillMaxWidth(),
                                    contentScale = ContentScale.Crop
                                )
                                Text(
                                    text = movie.title,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MovieDetailsScreen(
    movie: MovieDetails,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = onBack
        ) {
            Text("Volver")
        }
        Text(
            text = movie.title,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
            contentDescription = movie.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = movie.overview
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Géneros: ${movie.genres.joinToString { it.name }}"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Puntuación: ${movie.voteAverage}"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PeliculasAppTheme {
        Greeting()
    }
}