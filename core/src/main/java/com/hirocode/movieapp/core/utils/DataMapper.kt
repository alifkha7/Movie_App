package com.hirocode.movieapp.core.utils

import com.hirocode.movieapp.core.data.source.local.entity.MovieEntity
import com.hirocode.movieapp.core.data.source.remote.response.MovieResponse
import com.hirocode.movieapp.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                overview = it.overview,
                title = it.title,
                releaseDate = it.releaseDate,
                posterPath = it.posterPath,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                overview = it.overview,
                title = it.title,
                releaseDate = it.releaseDate,
                posterPath = it.posterPath,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        overview = input.overview,
        title = input.title,
        releaseDate = input.releaseDate,
        posterPath = input.posterPath,
        isFavorite = input.isFavorite
    )
}