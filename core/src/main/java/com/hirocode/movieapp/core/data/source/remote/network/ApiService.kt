package com.hirocode.movieapp.core.data.source.remote.network

import com.hirocode.movieapp.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getMovie(
        @Query("api_key") api_key: String,
        @Query("region") region: String,
    ): ListMovieResponse
}