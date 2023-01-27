package com.uraga.moviestmdb.presentation.movies_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uraga.data_layer.mappers.toDomain
import com.uraga.domain.models.request.AuthenticationRequest
import com.uraga.domain.models.response.RequestTokenResponse
import com.uraga.use_cases_layer.MoviesUseCases
import com.uraga.domain.util.Resource
import com.uraga.moviestmdb.BuildConfig
import com.uraga.moviestmdb.contants.MoviesListConstants.LANGUAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val moviesUseCases: MoviesUseCases
) : ViewModel() {

    private val _state = mutableStateOf(MovieListState())
    var state: State<MovieListState> = _state

    init {
        getRequestToken()
    }

    private fun getRequestToken() {
        viewModelScope.launch {
            when (val result = moviesUseCases.getRequestToken()) {
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        hasError = true,
                        errorMessage = result.message
                    )
                }
                is Resource.Success -> {
                    getAccessToken(result.data)
                }
            }
        }
    }

    private fun getAccessToken(data: RequestTokenResponse?) {
        viewModelScope.launch {
            when (val result =
                moviesUseCases.getAccessToken(AuthenticationRequest(data?.requestToken))) {
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        hasError = true,
                        errorMessage = result.message
                    )
                }
                is Resource.Success -> {
                    getMoviesList()
                }
            }
        }
    }

    private fun getMoviesList() {
        viewModelScope.launch {
            when (val moviesResponse = moviesUseCases.getListMovies(
                apiKey = BuildConfig.API_KEY,
                LANGUAGE
            )) {
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        hasError = true,
                        errorMessage = moviesResponse.message
                    )
                }
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        hasError = false,
                        moviesList = moviesResponse.data?.result?.map { it.toDomain() } ?: emptyList()
                    )
                }
            }
        }
    }

}