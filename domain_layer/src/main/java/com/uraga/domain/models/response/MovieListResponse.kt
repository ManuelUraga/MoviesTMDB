package com.uraga.domain.models.response

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("results") var result: List<ResultResponse>?=null
)
