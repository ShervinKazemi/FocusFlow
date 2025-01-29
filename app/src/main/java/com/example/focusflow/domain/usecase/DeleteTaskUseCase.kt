package com.example.focusflow.domain.usecase

import com.example.focusflow.domain.model.Task
import com.example.focusflow.domain.repository.TaskRepository

class DeleteTaskUseCase(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(task: Task) =
        taskRepository.deleteTask(task)

}