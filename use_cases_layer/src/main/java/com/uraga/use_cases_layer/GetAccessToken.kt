package com.uraga.use_cases_layer

import com.uraga.domain.models.request.AuthenticationRequest
import com.uraga.domain.models.response.AccessTokenResponse
import com.uraga.domain.repository.MoviesRepository
import com.uraga.domain.util.Resource

class GetAccessToken (private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(
        request: AuthenticationRequest
    ): Resource<AccessTokenResponse> {
        return moviesRepository.accessToken(request)
    }
}