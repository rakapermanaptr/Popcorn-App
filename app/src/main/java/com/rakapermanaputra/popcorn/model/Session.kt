package com.rakapermanaputra.popcorn.model

import com.google.gson.annotations.SerializedName

data class Session(
    @SerializedName("session_id")
    var sessionId: String,
    @SerializedName("success")
    var success: Boolean
)