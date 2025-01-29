package com.example.focusflow.util

import com.example.focusflow.data.local.entity.TaskEntity
import com.example.focusflow.domain.model.Task

fun TaskEntity.toDomainModel(): Task {
    return Task(
        id = id,
        title = title,
        isDone = isDone,
        repeatDays = repeatDays,
        dateCreated = dateCreated,
        timeCreated = timeCreated
    )
}

fun Task.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        isDone = isDone,
        repeatDays = repeatDays,
        dateCreated = dateCreated,
        timeCreated = timeCreated
    )
}