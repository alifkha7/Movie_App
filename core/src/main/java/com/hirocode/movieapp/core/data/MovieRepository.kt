package com.hirocode.movieapp.core.data

import com.hirocode.movieapp.core.data.source.local.LocalDataSource
import com.hirocode.movieapp.core.data.source.remote.RemoteDataSource
import com.hirocode.movieapp.core.data.source.remote.network.ApiResponse
import com.hirocode.movieapp.core.data.source.remote.response.MovieResponse
import com.hirocode.movieapp.core.domain.model.Movie
import com.hirocode.movieapp.core.domain.repository.IMovieRepository
import com.hirocode.movieapp.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IMovieRepository {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.setFavoriteMovie(movieEntity, state)
        }
    }
}

