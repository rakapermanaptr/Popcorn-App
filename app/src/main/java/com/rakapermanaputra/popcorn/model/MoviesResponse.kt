package com.rakapermanaputra.popcorn.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("results") var results: List<Movies>,
    @SerializedName("page") val page: Int,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int
)