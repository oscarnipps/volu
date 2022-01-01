package com.app.volu.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EventCategory")
data class EventCategoryEntity(

    val id: String,

    @PrimaryKey(autoGenerate = false)
    val categoryName: String,

    val categoryImage: String,
)