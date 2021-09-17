package com.example.volu.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventCategory(

    val id: Int,

    @PrimaryKey(autoGenerate = false)
    val categoryName: String,

    val categoryImage: String,
)