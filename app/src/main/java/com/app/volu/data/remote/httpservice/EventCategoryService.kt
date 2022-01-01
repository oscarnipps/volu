package com.app.volu.data.remote.httpservice

import com.app.volu.data.remote.response.Data
import com.app.volu.data.remote.response.EventCategoryGetResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface EventCategoryService {

    @GET("events/categories")
    fun getEventCategoryFromApi() : Single<Response<Data<List<EventCategoryGetResponse>>>>
}