package com.example.focusflow.domain.usecase

import com.example.focusflow.domain.model.Task
import com.example.focusflow.domain.repository.TaskRepository

class AddTaskUseCase(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(task: Task) =
        taskRepository.insertTask(task)

}