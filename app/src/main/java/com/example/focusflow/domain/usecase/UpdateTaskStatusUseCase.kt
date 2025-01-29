package com.example.focusflow.domain.usecase

import com.example.focusflow.domain.repository.TaskRepository

class UpdateTaskStatusUseCase(
    private val taskRepository: TaskRepository,
) {

    suspend operator fun invoke(taskId: Int, isDone: Boolean) =
        taskRepository.updateTaskStatus(taskId, isDone)

}