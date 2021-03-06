package com.app.volu.data.remote.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserLoginResponse(
    @SerializedName("access_token")
    @Expose
    val accessToken: String,

    @SerializedName("id")
    @Expose
    val id: String
)