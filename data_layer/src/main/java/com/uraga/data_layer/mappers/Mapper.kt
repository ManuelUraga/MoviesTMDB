package com.uraga.data_layer.mappers

import com.uraga.domain.models.response.ResultResponse
import com.uraga.domain.models.Movie

fun ResultResponse.toDomain(): Movie {
    return Movie(
        adult = adult ?: false,
        backdropPath = backdropPath ?: "",
        genreIds = genreIds ?: emptyList(),
        id = id ?: 0,
        mediaType = mediaType ?: "",
        originalLanguage = originalLanguage ?: "",
        originalTitle = originalTitle ?: "",
        overview = overview ?: "",
        popularity = popularity ?: 0f,
        posterPath = posterPath ?: "",
        releaseDate = releaseDate ?: "",
        title = title ?: "",
        video = video ?: false,
        voteAverage = voteAverage ?: 0f,
        voteCount = voteCount ?: 0
    )
}