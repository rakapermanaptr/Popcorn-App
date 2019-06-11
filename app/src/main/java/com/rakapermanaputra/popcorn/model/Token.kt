package com.rakapermanaputra.popcorn.model

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("expires_at")
    val expiresAt: String,
    @SerializedName("request_token")
    val requestToken: String,
    @SerializedName("success")
    val success: Boolean
)