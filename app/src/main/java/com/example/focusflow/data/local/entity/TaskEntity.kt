package com.example.focusflow.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.focusflow.data.local.converter.TaskConverters

@Entity(tableName = "tasks")
@TypeConverters(TaskConverters::class)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "is_done")
    val isDone: Boolean = false,

    @ColumnInfo(name = "repeat_days")
    val repeatDays: List<String>? = emptyList(),

    @ColumnInfo(name = "date_created")
    val dateCreated: String,

    @ColumnInfo(name = "time_created")
    val timeCreated: String,
)