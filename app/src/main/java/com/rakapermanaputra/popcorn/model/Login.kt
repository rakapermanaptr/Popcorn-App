package com.rakapermanaputra.popcorn.model

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("password")
    val password: String,
    @SerializedName("request_token")
    val requestToken: String,
    @SerializedName("username")
    val username: String
)