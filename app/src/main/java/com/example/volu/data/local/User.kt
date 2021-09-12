package com.example.volu.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val serverId: String,

    val firstName: String,

    val lastName: String,

    val email: String,

    val sex: String,

    val phone: String
)
