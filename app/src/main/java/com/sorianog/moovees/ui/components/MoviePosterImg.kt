package com.sorianog.moovees.ui.components

import androidx.compose.foundation.Image
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

enum class PosterSize(
    basePath: String
) {
    SMALL(ApiConstants.IMG_SMALL_BASE_URL),
    LARGE(ApiConstants.IMG_LARGE_BASE_URL)
}

@Composable
internal fun MoviePoster(
    posterPath: String?,
    posterSize: PosterSize = PosterSize.SMALL
) {
    if (!posterPath.isNullOrBlank()) {
        val isSmall = posterSize == PosterSize.SMALL
        AsyncImage(
            modifier = if (isSmall) Modifier.height(200.dp) else Modifier.height(300.dp),
            model = getPosterUrl(posterSize, posterPath),
            contentDescription = stringResource(R.string.movie_poster),
            placeholder = painterResource(R.drawable.photo_img),
            error = painterResource(R.drawable.photo_img),
            contentScale = if (isSmall) ContentScale.Fit else ContentScale.FillHeight
        )
    } else {
        Image(
            painterResource(R.drawable.photo_img),
            contentDescription = stringResource(R.string.movie_poster)
        )
    }
}

private fun getPosterUrl(size: PosterSize, posterPath: String): String {
    return when (size) {
        PosterSize.SMALL -> {
            "${ApiConstants.IMG_SMALL_BASE_URL}${posterPath}"
        }
        PosterSize.LARGE -> {
            "${ApiConstants.IMG_LARGE_BASE_URL}${posterPath}"
        }
    }
}