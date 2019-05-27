package com.rakapermanaputra.popcorn.model

import com.google.gson.annotations.SerializedName

data class TvShowsResponse(
    @SerializedName("results")
    var results: List<TvShows>
)