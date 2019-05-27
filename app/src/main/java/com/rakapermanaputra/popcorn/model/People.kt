package com.rakapermanaputra.popcorn.model

import com.google.gson.annotations.SerializedName

data class People(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String?,
    @SerializedName("popularity")
    var popularity: Double?,
    @SerializedName("profile_path")
    var profilePath: String?
)