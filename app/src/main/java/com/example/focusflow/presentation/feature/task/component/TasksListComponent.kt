package com.example.focusflow.presentation.feature.task.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.focusflow.domain.model.Task

@Composable
fun TasksListComponent(
    tasks: List<Task>,
    currentDay: String,
    isLocked: Boolean,
    onTaskChecked: (Int, Boolean, String) -> Unit,
    onDeleteTask: (Task) -> Unit,
) {
    if (tasks.isEmpty()) {
        AnimationComponent()
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(
                items = tasks,
                key = { it.id }
            ) { task ->
                TaskItemComponent(
                    task = task,
                    currentDay = currentDay,
                    isLocked = isLocked,
                    onTaskChecked = onTaskChecked,
                    onDeleteTask = onDeleteTask
                )
            }
        }
    }
}