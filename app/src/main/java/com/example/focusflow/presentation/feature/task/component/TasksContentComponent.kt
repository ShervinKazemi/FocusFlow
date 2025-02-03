package com.example.focusflow.presentation.feature.task.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.focusflow.domain.enums.WeekDay
import com.example.focusflow.domain.model.Task
import com.example.focusflow.presentation.feature.task.TasksUiState

@Composable
fun TasksContentComponent(
    paddingValues: PaddingValues,
    selectedDay: WeekDay,
    isLocked: Boolean,
    tasks: List<Task>,
    uiState: TasksUiState,
    currentDay: String,
    onDaySelected: (WeekDay) -> Unit,
    onTaskChecked: (Int, Boolean, String) -> Unit,
    onDeleteTask: (Task) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        DaySelectorComponent(
            selectedDay = selectedDay,
            currentDay = currentDay,
            onDaySelected = onDaySelected
        )
        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            TasksListComponent(
                tasks = tasks,
                currentDay = currentDay,
                isLocked = isLocked,
                onTaskChecked = onTaskChecked,
                onDeleteTask = onDeleteTask
            )
        }
    }
}