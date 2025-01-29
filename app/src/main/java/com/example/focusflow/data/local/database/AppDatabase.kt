package com.example.focusflow.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.focusflow.data.local.converter.TaskConverters
import com.example.focusflow.data.local.dao.TaskDao
import com.example.focusflow.data.local.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
@TypeConverters(TaskConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}