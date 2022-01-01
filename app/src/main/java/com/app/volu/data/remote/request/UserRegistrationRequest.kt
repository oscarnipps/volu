package com.app.volu.data.remote.request


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserRegistrationRequest(
    @SerializedName("email")
    @Expose
    val email: String,
    @SerializedName("first_name")
    @Expose
    val firstName: String,
    @SerializedName("last_name")
    @Expose
    val lastName: String,
    @SerializedName("password")
    @Expose
    val password: String,
    @SerializedName("phone")
    @Expose
    val phone: String,
    @SerializedName("sex")
    @Expose
    val sex: String
)