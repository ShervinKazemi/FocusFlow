@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.focusflow.presentation.feature.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.focusflow.data.local.dao.TaskDao
import com.example.focusflow.domain.enums.WeekDay
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class TasksViewModel(
    private val taskDao: TaskDao
) : ViewModel() {
    private val _selectedDay = MutableStateFlow(WeekDay.MONDAY)
    val selectedDay :StateFlow<WeekDay> = _selectedDay.asStateFlow()

    val tasksForSelectedDay = selectedDay.flatMapLatest { day ->
        taskDao.getTasksByRepeatDay(day.fullName)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun selectDay(day: WeekDay) {
        _selectedDay.value = day
    }
}