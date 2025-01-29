package com.example.focusflow.domain.usecase

import com.example.focusflow.domain.repository.TaskRepository

class GetTasksByRepeatDayUseCase(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(day: String) =
        taskRepository.getTasksByRepeatDay(day)

}