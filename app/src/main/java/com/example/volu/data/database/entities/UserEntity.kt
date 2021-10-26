package com.example.volu.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "User")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val serverId: String,

    val firstName: String,

    val lastName: String,

    val email: String,

    val sex: String,

    val phone: String
)
