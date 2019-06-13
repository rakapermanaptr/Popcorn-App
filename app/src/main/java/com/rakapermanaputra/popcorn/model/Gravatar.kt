package com.rakapermanaputra.popcorn.model

import com.google.gson.annotations.SerializedName

data class Gravatar(
    @SerializedName("hash")
    val hash: String
)