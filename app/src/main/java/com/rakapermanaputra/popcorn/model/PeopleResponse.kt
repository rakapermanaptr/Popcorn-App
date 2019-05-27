package com.rakapermanaputra.popcorn.model

import com.google.gson.annotations.SerializedName

data class PeopleResponse(
    @SerializedName("results")
    var results: List<People>
)