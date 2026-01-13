package com.sorianog.moovees.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailModel(
    var title: String? = null,
    var overview: String? = null,
    @SerialName("release_date") var releaseDate: String? = null,
    @SerialName("backdrop_path") var backdropPath: String? = null
)