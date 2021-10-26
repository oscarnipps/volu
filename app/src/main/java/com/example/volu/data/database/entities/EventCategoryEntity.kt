package com.example.volu.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EventCategory")
data class EventCategoryEntity(

    val id: Int,

    @PrimaryKey(autoGenerate = false)
    val categoryName: String,

    val categoryImage: String,
)