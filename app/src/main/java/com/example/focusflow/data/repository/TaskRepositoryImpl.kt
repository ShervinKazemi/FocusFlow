package com.example.focusflow.data.repository

import com.example.focusflow.data.local.dao.TaskDao
import com.example.focusflow.domain.model.Task
import com.example.focusflow.domain.repository.TaskRepository
import com.example.focusflow.util.toDomainModel
import com.example.focusflow.util.toEntity

class TaskRepositoryImpl(
    private val taskDao: TaskDao
) :TaskRepository {
    override suspend fun getTasksForDay(day: String): List<Task> =
        taskDao.getTasksForDay(day).map { it.toDomainModel() }

    override suspend fun insertTask(task: Task) =
        taskDao.insertTask(task.toEntity())

    override suspend fun deleteTask(task: Task) =
        taskDao.deleteTask(task.toEntity())

    override suspend fun deleteAllTasks() =
        taskDao.deleteAllTasks()

    override suspend fun updateTaskStatus(taskId: Int, isDone: Boolean , day: String) =
        taskDao.updateTaskStatus(taskId , isDone , day)
}