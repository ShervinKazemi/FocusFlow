package com.example.focusflow.domain.repository

import com.example.focusflow.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun getAllTasks() :Flow<List<Task>>
    suspend fun getTasksByRepeatDay(day: String) :Flow<List<Task>>
    suspend fun insertTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun updateTaskStatus(taskId: Int , isDone: Boolean)
}