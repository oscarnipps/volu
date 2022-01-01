package com.app.volu.data.remote.response


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EventCategoryGetResponse(
    @SerializedName("category_name")
    @Expose
    val categoryName: String,

    @SerializedName("_id")
    @Expose
    val id: String,

    @SerializedName("image_url")
    @Expose
    val imageUrl: String?
)