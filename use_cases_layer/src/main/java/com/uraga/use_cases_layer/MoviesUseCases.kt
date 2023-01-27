package com.uraga.use_cases_layer

data class MoviesUseCases(
    val getRequestToken: GetRequestToken,
    val getAccessToken: GetAccessToken,
    val getListMovies: GetListMovies
)
