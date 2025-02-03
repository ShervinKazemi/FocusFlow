package com.example.focusflow.domain.repository

import com.example.focusflow.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun getTasksForDay(day: String) :List<Task>
    suspend fun insertTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun deleteAllTasks()
    suspend fun updateTaskStatus(taskId: Int , isDone: Boolean , day: String)
}