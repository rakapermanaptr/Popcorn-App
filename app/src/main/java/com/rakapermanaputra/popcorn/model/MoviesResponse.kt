package com.rakapermanaputra.popcorn.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("results")
    var results: List<Movies>
)