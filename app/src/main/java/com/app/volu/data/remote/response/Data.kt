package com.app.volu.data.remote.response


import com.google.gson.annotations.SerializedName

data class Data<T>(
    /*@SerializedName("data")
    val userLoginResponse: UserLoginResponse,*/

    @SerializedName("data")
    val data: T,

    @SerializedName("status")
    val status: String,

    @SerializedName("message")
    val message: String
)