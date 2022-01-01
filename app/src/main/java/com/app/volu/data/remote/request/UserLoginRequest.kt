package com.app.volu.data.remote.request


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserLoginRequest(
    @SerializedName("email")
    @Expose
    val email: String,

    @SerializedName("password")
    @Expose
    val password: String
)