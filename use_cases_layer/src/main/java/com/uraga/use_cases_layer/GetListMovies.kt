package com.uraga.use_cases_layer

import com.uraga.domain.models.response.MovieListResponse
import com.uraga.domain.repository.MoviesRepository
import com.uraga.domain.util.Resource

class GetListMovies (private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(
        apiKey: String,
        language: String
    ): Resource<MovieListResponse> {
        return moviesRepository.getMovies(apiKey, language)
    }
}