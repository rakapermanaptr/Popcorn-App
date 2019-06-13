package com.rakapermanaputra.popcorn.model

import com.google.gson.annotations.SerializedName

data class Avatar(
    @SerializedName("gravatar")
    val gravatar: Gravatar
)