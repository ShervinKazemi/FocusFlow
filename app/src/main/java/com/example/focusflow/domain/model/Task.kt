package com.example.focusflow.domain.model

import com.example.focusflow.data.local.converter.TaskConverters
import com.example.focusflow.domain.enums.WeekDay

data class Task(
    val id: Int = 0,
    val title: String,
    val isDone: Boolean = false,
    val repeatDays: List<WeekDay> = emptyList(),
    val dateCreated: String = TaskConverters.getCurrentDateString(),
    val timeCreated: String = TaskConverters.getCurrentTimeString()
)