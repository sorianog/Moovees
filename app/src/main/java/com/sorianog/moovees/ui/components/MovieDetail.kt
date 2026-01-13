package com.sorianog.moovees.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.sorianog.moovees.R
import com.sorianog.moovees.data.api.ApiConstants
import com.sorianog.moovees.data.entity.MovieDetailModel

@Composable
fun MovieDetail(
    movieDetail: MovieDetailModel
) {
    Column(modifier = Modifier.fillMaxSize()) {
        MovieBackdropImg(movieDetail.backdropPath)
    }
}

@Composable
fun MovieBackdropImg(
    backdropPath: String?
) {
    if (!backdropPath.isNullOrBlank()) {
        val imgUrl = "${ApiConstants.IMG_LARGE_BASE_URL}$backdropPath"
        AsyncImage(
            modifier = Modifier.fillMaxWidth().height(300.dp),
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