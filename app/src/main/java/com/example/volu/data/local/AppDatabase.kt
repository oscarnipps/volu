package com.example.volu.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.volu.data.dao.AuthDao
import com.example.volu.data.dao.UserDao

@Database(
    entities = [
        User::class,
        EventCategory::class
    ],
    exportSchema = true,
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun authDao(): AuthDao
}