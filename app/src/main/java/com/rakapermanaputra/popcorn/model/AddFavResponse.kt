package com.rakapermanaputra.popcorn.model

import com.google.gson.annotations.SerializedName

data class AddFavResponse(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String
)