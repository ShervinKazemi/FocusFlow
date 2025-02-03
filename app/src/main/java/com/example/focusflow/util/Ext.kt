package com.example.focusflow.util

import android.util.Log
import com.example.focusflow.domain.enums.WeekDay
import kotlinx.coroutines.CoroutineExceptionHandler

val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
    Log.v("error", "Error -> ${throwable.message}")
}

fun String.toWeekDay() : WeekDay? {
    return WeekDay.entries.find { it.name.equals(this , ignoreCase = false) }
}