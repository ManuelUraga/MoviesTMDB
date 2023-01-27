package com.uraga.domain.repository


import com.uraga.domain.models.request.AuthenticationRequest
import com.uraga.domain.models.response.AccessTokenResponse
import com.uraga.domain.models.response.MovieListResponse
import com.uraga.domain.models.response.RequestTokenResponse
import com.uraga.domain.util.Resource


interface MoviesRepository {

    suspend fun requestToken(): Resource<RequestTokenResponse>

    suspend fun accessToken(
        request: AuthenticationRequest
    ): Resource<AccessTokenResponse>

    suspend fun getMovies(
        apiKey: String,
        language: String
    ): Resource<MovieListResponse>

}