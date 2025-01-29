package com.example.focusflow.domain.usecase

import com.example.focusflow.domain.repository.TaskRepository

class GetAllTasksUseCase(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke() =
        taskRepository.getAllTasks()

}