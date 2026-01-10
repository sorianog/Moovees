package com.sorianog.moovees.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.sorianog.moovees.R
import com.sorianog.moovees.data.api.ApiConstants
import com.sorianog.moovees.data.entity.MovieModel

@Composable
fun MovieList(
    movieList: List<MovieModel>
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(movieList, key = { movie -> movie.id }) { movie ->
            MovieListItem(movie)
        }
    }
}

@Composable
fun MovieListItem(
    movie: MovieModel,
    modifier: Modifier = Modifier
) {
    HorizontalDivider()
    ListItem(
        leadingContent = {
            MoviePoster(movie.posterPath)
        },
        headlineContent = {
            MovieTitle(movie.title)
        },
        supportingContent = {
            Column {
                MovieDescription(modifier, movie.overview)
                MovieDate(movie.releaseDate)
            }
        },
    )
    HorizontalDivider()
}

@Composable
private fun MovieTitle(title: String?) {
    Text(
        text = title ?: stringResource(R.string.no_title),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun MovieDescription(
    modifier: Modifier,
    description: String?
) {
    Text(
        modifier = modifier.padding(top = 6.dp, bottom = 6.dp),
        text = description ?: stringResource(R.string.no_desc),
        fontStyle = FontStyle.Italic
    )
}

@Composable
private fun MovieDate(date: String?) {
    date?.let { date ->
        Text(
            text = stringResource(R.string.movie_date, date),
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun MoviePoster(posterPath: String?) {
    if (!posterPath.isNullOrBlank()) {
        AsyncImage(
            modifier = Modifier.height(100.dp),
            model = "${ApiConstants.IMG_SMALL_BASE_URL}${posterPath}",
            contentDescription = stringResource(R.string.movie_poster),
            placeholder = painterResource(R.drawable.photo_img),
            error = painterResource(R.drawable.photo_img)
        )
    } else {
        Image(
            painterResource(R.drawable.photo_img),
            contentDescription = stringResource(R.string.movie_poster)
        )
    }
}