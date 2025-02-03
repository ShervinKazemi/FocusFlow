package com.example.focusflow.domain.usecase

import com.example.focusflow.domain.repository.TaskRepository

class DeleteAllTasksUseCase(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke() =
        taskRepository.deleteAllTasks()

}