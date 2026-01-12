package com.sorianog.moovees.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sorianog.moovees.R
import com.sorianog.moovees.data.entity.MovieModel

@Composable
fun MovieGrid(
    movies: List<MovieModel>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(movies, key = { movie -> movie.id }) { movie ->
            MovieGridItem(movie)
        }
    }
}

@Composable
fun MovieGridItem(
    movie: MovieModel
) {
    Column(
        modifier = Modifier.padding(6.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MoviePoster(movie.posterPath, PosterSize.LARGE)
        MovieTitleLabel(movie.title)
    }
}

@Composable
fun MovieTitleLabel(title: String?) {
    Text(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize()
            .padding(6.dp),
        text = title ?: stringResource(R.string.no_title),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}