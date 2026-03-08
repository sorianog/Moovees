package com.sorianog.moovees.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieModelLocal(
    @PrimaryKey
    val id: Int,
    val title: String?,
    val overview: String?,
    val releaseDate: String?,
    val backdropPath: String?,
    val posterPath: String?,
    val runtime: Int?,
    val homepage: String?,
    val marked: Boolean,
    val markedOn: String
)