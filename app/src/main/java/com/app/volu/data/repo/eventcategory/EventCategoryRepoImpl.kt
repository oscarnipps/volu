package com.app.volu.data.repo.eventcategory

import com.app.volu.data.database.entities.EventCategoryEntity
import com.app.volu.data.mappers.EventCategoryNetworkMapper
import com.app.volu.data.remote.httpservice.EventCategoryService
import com.app.volu.data.remote.response.Data
import com.google.gson.GsonBuilder
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class EventCategoryRepoImpl @Inject constructor(private val eventCategoryService: EventCategoryService) :
    EventCategoryRepo {

    //todo :  inject with hilt
    val mapper = EventCategoryNetworkMapper()

    override fun getEventCategoryFromApi(): Single<Result<List<EventCategoryEntity>>> {
        return eventCategoryService.getEventCategoryFromApi()
            .map { eventCategoryResponse ->

                if (eventCategoryResponse.isSuccessful) {
                    val data = eventCategoryResponse.body()?.data

                    Timber.d("category body : ${eventCategoryResponse.body()}")

                    data ?: return@map Result.failure(Throwable("data list empty"))

                    Timber.d("category body data : $data")

                    return@map Result.success(mapper.mapToDomainList(data))
                }

                val gson = GsonBuilder().create()

                val data = gson.fromJson(eventCategoryResponse.errorBody()?.charStream(), Data::class.java)

                Timber.d("category error message : ${data.message}")

                Timber.d("category error status : ${data.status}")

                Result.failure(Throwable(data.message))
            }
    }
}