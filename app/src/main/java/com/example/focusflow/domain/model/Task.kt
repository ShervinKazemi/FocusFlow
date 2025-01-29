package com.example.focusflow.domain.model

data class Task(
    val id: Int = 0,
    val title: String,
    val isDone: Boolean = false,
    val repeatDays: List<String>? = emptyList(),
    val dateCreated: Long = System.currentTimeMillis(),
    val timeCreated: Long = System.currentTimeMillis()
)