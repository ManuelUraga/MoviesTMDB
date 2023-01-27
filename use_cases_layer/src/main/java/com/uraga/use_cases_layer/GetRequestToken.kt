package com.uraga.use_cases_layer

import com.uraga.domain.models.response.RequestTokenResponse
import com.uraga.domain.repository.MoviesRepository
import com.uraga.domain.util.Resource

class GetRequestToken(
    private val moviesRepository: MoviesRepository
) {
    suspend operator fun invoke(): Resource<RequestTokenResponse> {
        return moviesRepository.requestToken()
    }
}