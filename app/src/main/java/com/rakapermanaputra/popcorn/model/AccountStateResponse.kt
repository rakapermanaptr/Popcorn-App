package com.rakapermanaputra.popcorn.model

import com.google.gson.annotations.SerializedName

data class AccountStateResponse(
    @SerializedName("favorite")
    var favorite: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("rated")
    val rated: Boolean,
    @SerializedName("watchlist")
    val watchlist: Boolean
)