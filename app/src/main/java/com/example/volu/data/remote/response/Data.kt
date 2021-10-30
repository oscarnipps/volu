package com.example.volu.data.remote.response


import com.google.gson.annotations.SerializedName

data class Data<T>(
    /*@SerializedName("data")
    val userLoginResponse: UserLoginResponse,*/

    @SerializedName("data")
    val response: T,

    @SerializedName("status")
    val status: String
)