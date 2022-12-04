package com.hirocode.movieapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    val movieId: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val posterPath: String,
    val isFavorite: Boolean
) : Parcelable