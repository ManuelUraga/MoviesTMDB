package com.uraga.domain.models.response

import com.google.gson.annotations.SerializedName

data class RequestTokenResponse(
    @SerializedName("request_token") val requestToken: String? = null,
    @SerializedName("success") val success: Boolean? = null,
    @SerializedName("status_code") val statusCode: Int? = null,
)
