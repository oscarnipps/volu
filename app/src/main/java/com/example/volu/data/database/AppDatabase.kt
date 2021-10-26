package com.example.volu.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.volu.data.dao.AuthDao
import com.example.volu.data.dao.UserDao
import com.example.volu.data.database.entities.EventCategoryEntity
import com.example.volu.data.database.entities.UserEntity

@Database(
    entities = [
        UserEntity::class,
        EventCategoryEntity::class
    ],
    exportSchema = true,
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun authDao(): AuthDao
}