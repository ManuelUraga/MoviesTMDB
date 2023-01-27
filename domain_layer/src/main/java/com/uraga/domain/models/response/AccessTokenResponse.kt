package com.uraga.domain.models.response

import com.google.gson.annotations.SerializedName

data class AccessTokenResponse(
    @SerializedName("request_token") var requestToken: String? = null,
    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("status_code") var statusCode: Int? = null,
)