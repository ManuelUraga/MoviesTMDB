package com.uraga.data_layer.repository

import com.uraga.data_layer.network.MoviesApiServices
import com.uraga.domain.util.BaseRepo
import com.uraga.domain.util.Resource
import com.uraga.domain.models.request.AuthenticationRequest
import com.uraga.domain.models.request.TokenRequest
import com.uraga.domain.models.response.AccessTokenResponse
import com.uraga.domain.models.response.MovieListResponse
import com.uraga.domain.models.response.RequestTokenResponse
import com.uraga.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val moviesApiServices: MoviesApiServices
) : MoviesRepository, BaseRepo() {
    override suspend fun requestToken(): Resource<RequestTokenResponse> {
        return safeApiCall { moviesApiServices.requestToken(TokenRequest("http://www.themoviedb.org/"))}
    }

    override suspend fun accessToken(request: AuthenticationRequest): Resource<AccessTokenResponse> {
        return safeApiCall { moviesApiServices.accessToken(request) }
    }

    override suspend fun getMovies(
        apiKey: String,
        language: String
    ): Resource<MovieListResponse> {
        return safeApiCall { moviesApiServices.getLisMovies(apiKey, language) }
    }
}