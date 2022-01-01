package com.app.volu.data.mappers

import com.app.volu.data.database.entities.EventCategoryEntity
import com.app.volu.data.remote.response.EventCategoryGetResponse

class EventCategoryNetworkMapper {

    //fun mapToDomain(remote : EventCategoryGetResponse) : EventCategoryEntity {}

    fun mapToDomainList(remote: List<EventCategoryGetResponse>): List<EventCategoryEntity> {
        val categoryList = mutableListOf<EventCategoryEntity>()

        for (data in remote) {
            categoryList.add(
                EventCategoryEntity(data.id, data.categoryName, data.imageUrl ?: "")
            )
        }

        return categoryList
    }

    //fun mapToRemote(remote : Remote): Domain {}
}