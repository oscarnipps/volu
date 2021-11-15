package com.app.volu.data.remote.response


import com.google.gson.annotations.SerializedName

data class UserLoginResponse(
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("id")
    val id: String
)