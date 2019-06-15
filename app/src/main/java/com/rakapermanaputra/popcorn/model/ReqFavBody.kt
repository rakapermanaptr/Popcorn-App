package com.rakapermanaputra.popcorn.model

import com.google.gson.annotations.SerializedName

data class ReqFavBody(
    @SerializedName("favorite")
    val favorite: Boolean,
    @SerializedName("media_id")
    val mediaId: Int,
    @SerializedName("media_type")
    val mediaType: String
)