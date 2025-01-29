package com.example.focusflow.data.repository

import com.example.focusflow.data.local.dao.TaskDao
import com.example.focusflow.domain.model.Task
import com.example.focusflow.domain.repository.TaskRepository
import com.example.focusflow.util.toDomainModel
import com.example.focusflow.util.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
    private val taskDao: TaskDao
) :TaskRepository {
    override suspend fun getAllTasks(): Flow<List<Task>> =
        taskDao.getAllTasks().map { it -> it.map { it.toDomainModel() } }

    override suspend fun getTasksByRepeatDay(day: String): Flow<List<Task>> =
        taskDao.getTasksByRepeatDay(day).map { it -> it.map { it.toDomainModel() } }

    override suspend fun insertTask(task: Task) =
        taskDao.insertTask(task.toEntity())

    override suspend fun deleteTask(task: Task) =
        taskDao.deleteTask(task.toEntity())

    override suspend fun updateTaskStatus(taskId: Int, isDone: Boolean) =
        taskDao.updateTaskStatus(taskId , isDone)
}