package com.uraga.data_layer.network

import com.uraga.domain.models.request.AuthenticationRequest
import com.uraga.domain.models.request.TokenRequest
import com.uraga.domain.models.response.AccessTokenResponse
import com.uraga.domain.models.response.MovieListResponse
import com.uraga.domain.models.response.RequestTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MoviesApiServices {

    @GET("list/1?")
    suspend fun getLisMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<MovieListResponse>

    @POST("auth/request_token")
    suspend fun requestToken(@Body body: TokenRequest): Response<RequestTokenResponse>

    @POST("auth/request_token")
    suspend fun accessToken(@Body body: AuthenticationRequest): Response<AccessTokenResponse>
}