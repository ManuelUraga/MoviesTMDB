package com.uraga.moviestmdb.presentation.movies_list

import com.uraga.domain.models.Movie

data class MovieListState (
    val hasError: Boolean = false,
    val errorMessage: String = "",
    val moviesList: List<Movie> = emptyList()
)