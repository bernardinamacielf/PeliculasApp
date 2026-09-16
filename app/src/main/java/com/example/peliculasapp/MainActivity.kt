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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

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
fun Greeting(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Películas populares"
        )
        Card {
            Column {
                Text(
                    text = "Película de prueba 1"
                )
                AsyncImage(
                    model = "https://picsum.photos/200/300",
                    contentDescription = "Imagen de prueba",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Card {
            Column {
                Text(
                    text = "Película de prueba 2"
                )
                AsyncImage(
                    model = "https://picsum.photos/200/300",
                    contentDescription = "Imagen de prueba",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Card {
            Column {
                Text(
                    text = "Película de prueba 3"
                )
                AsyncImage(
                    model = "https://picsum.photos/200/300",
                    contentDescription = "Imagen de prueba",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PeliculasAppTheme {
        Greeting()
    }
}