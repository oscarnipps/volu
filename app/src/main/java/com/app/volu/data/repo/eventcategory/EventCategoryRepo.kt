package com.app.volu.data.repo.eventcategory

import com.app.volu.data.database.entities.EventCategoryEntity
import io.reactivex.Single

interface EventCategoryRepo {

    fun getEventCategoryFromApi() : Single<Result<List<EventCategoryEntity>>>

    //todo: edit user event categories api request

}