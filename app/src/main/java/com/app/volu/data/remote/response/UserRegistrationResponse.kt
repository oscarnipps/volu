package com.app.volu.data.remote.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserRegistrationResponse(
    @SerializedName("access_token")
    @Expose
    val accessToken: String,
    @SerializedName("first_name")
    @Expose
    val firstName: String,
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("last_name")
    @Expose
    val lastName: String
)