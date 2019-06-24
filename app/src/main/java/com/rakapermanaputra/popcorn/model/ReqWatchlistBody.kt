package com.rakapermanaputra.popcorn.model

import com.google.gson.annotations.SerializedName

data class ReqWatchlistBody(
    @SerializedName("media_id")
    val mediaId: Int,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("watchlist")
    val watchlist: Boolean
)