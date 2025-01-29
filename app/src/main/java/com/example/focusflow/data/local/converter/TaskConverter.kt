package com.example.focusflow.data.local.converter

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class TaskConverters {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    @TypeConverter
    fun fromTimestamp(value: Long?): String? {
        return value?.let { dateFormat.format(Date(it)) }
    }

    @TypeConverter
    fun dateToTimestamp(dateStr: String?): Long? {
        return dateStr?.let {
            try {
                dateFormat.parse(it)?.time
            } catch (e: Exception) {
                null
            }
        }
    }

    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        return value?.joinToString(",")
    }

    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return value?.split(",")?.filter { it.isNotEmpty() }
    }

    companion object {
        // Utility function to get current date as formatted string
        fun getCurrentDateString(): String {
            return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .format(Date(System.currentTimeMillis()))
        }
    }
}