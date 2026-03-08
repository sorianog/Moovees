package com.sorianog.moovees.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkRemove
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.sorianog.moovees.R
import com.sorianog.moovees.data.api.ApiConstants
import com.sorianog.moovees.data.entity.MovieModelLocal

@Composable
fun MovieDetail(
    movieDetail: MovieModelLocal,
    markOnClick: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 10.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MovieBackdropImg(movieDetail.backdropPath, modifier)
        MovieTitleBanner(movieDetail.title, modifier)
        MovieSubInfo(movieDetail.releaseDate, movieDetail.runtime, modifier)
        MovieDescSection(movieDetail.overview, modifier)
        movieDetail.homepage?.let { MovieHomepage(it, modifier) }
        MarkMovieButton(movieDetail, markOnClick)
    }
}

@Composable
fun MovieBackdropImg(
    backdropPath: String?,
    modifier: Modifier = Modifier
) {
    if (!backdropPath.isNullOrBlank()) {
        val imgUrl = "${ApiConstants.IMG_LARGE_BASE_URL}$backdropPath"
        AsyncImage(
            modifier = modifier
                .fillMaxWidth()
                .height(300.dp),
            model = imgUrl,
            placeholder = painterResource(R.drawable.photo_img),
            error = painterResource(R.drawable.photo_img),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.movie_backdrop)
        )
    } else {
        Image(
            painterResource(R.drawable.photo_img),
            contentDescription = stringResource(R.string.movie_backdrop)
        )
    }
}

@Composable
fun MovieTitleBanner(
    title: String?,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(10.dp),
        text = title ?: stringResource(R.string.no_title),
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        textAlign = TextAlign.Center
    )
}

@Composable
fun MovieSubInfo(
    date: String?,
    runtime: Int?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .padding(top = 10.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        date?.let { date ->
            Text(
                text = stringResource(R.string.movie_date, date),
                fontWeight = FontWeight.SemiBold
            )
        }

        runtime?.let { time ->
            Text(
                text = stringResource(R.string.movie_runtime, time),
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun MovieDescSection(
    description: String?,
    modifier: Modifier
) {
    Text(
        modifier = modifier.padding(16.dp),
        text = description ?: stringResource(R.string.no_desc),
        fontStyle = FontStyle.Italic
    )
}

@Composable
fun MovieHomepage(
    homepage: String,
    modifier: Modifier
) {
    Text(
        buildAnnotatedString {
            withLink(
                LinkAnnotation.Url(
                    homepage,
                    TextLinkStyles(style = SpanStyle(color = Color.Blue))
                )
            ) {
                append(homepage)
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    )
}

@Composable
fun MarkMovieButton(
    movie: MovieModelLocal,
    markOnClick: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var isMovieMarked by remember { mutableStateOf(movie.marked) }

    Spacer(modifier = modifier.height(14.dp))
    TextButton(
        modifier = modifier
            .background(Color.Black, shape = MaterialTheme.shapes.medium),
        colors = ButtonDefaults.textButtonColors(contentColor = Color.White),
        onClick = {
            isMovieMarked = !isMovieMarked
            markOnClick(movie.id, isMovieMarked)
        }
    ) {
        Row(verticalAlignment =  Alignment.CenterVertically) {
            Icon(
                imageVector = if (isMovieMarked) Icons.Filled.BookmarkRemove
                else Icons.Outlined.BookmarkAdd,
                contentDescription = "Bookmark Icon",
                modifier = modifier.size(18.dp)
            )
            Spacer(modifier = modifier.width(8.dp))
            Text(
                text = if (isMovieMarked) stringResource(R.string.unmark_movie)
                else stringResource(
                    R.string.mark_movie
                )
            )
        }
    }
}