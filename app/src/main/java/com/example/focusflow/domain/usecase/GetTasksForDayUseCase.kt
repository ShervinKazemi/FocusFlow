package com.example.focusflow.domain.usecase

import com.example.focusflow.domain.model.Task
import com.example.focusflow.domain.repository.TaskRepository

class GetTasksForDayUseCase(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(day: String) :List<Task> =
        taskRepository.getTasksForDay(day)

}