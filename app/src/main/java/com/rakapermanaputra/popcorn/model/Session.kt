package com.rakapermanaputra.popcorn.model

import com.google.gson.annotations.SerializedName

data class Session(
    @SerializedName("session_id")
    val sessionId: String,
    @SerializedName("success")
    val success: Boolean
)