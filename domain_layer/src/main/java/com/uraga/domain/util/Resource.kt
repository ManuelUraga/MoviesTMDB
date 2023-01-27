package com.uraga.domain.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String
) {
    class Success<T>(data: T) : Resource<T>(data = data, "")
    class Error<T>(errorMessage: String) : Resource<T>(message = errorMessage)
}