package com.app.volu.data.remote.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data<T>(
    @SerializedName("data")
    @Expose
    val data: T,

    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("message")
    @Expose
    val message: String
)