package com.example.focusflow.presentation.feature.task

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.focusflow.domain.enums.WeekDay
import com.example.focusflow.presentation.feature.task.component.DeleteTaskDialogComponent
import com.example.focusflow.presentation.feature.task.component.TasksContentComponent
import com.example.focusflow.presentation.feature.task.component.TasksTopAppBarComponent
import com.example.focusflow.util.toWeekDay
import dev.burnoo.cokoin.navigation.getNavViewModel


@Composable
fun TasksScreen(
    viewModel: TasksViewModel = getNavViewModel<TasksViewModel>(),
    onNavigateToAddTask: () -> Unit,
) {
    val selectedDay by viewModel.selectedDay.collectAsState()
    val tasks by viewModel.tasks.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val currentDay by viewModel.currentDay.collectAsState()
    val isLocked = viewModel.isFutureDay(selectedDay , currentDay.toWeekDay() ?: WeekDay.MONDAY)

    Scaffold(
        topBar = {
            TasksTopAppBarComponent(
                onDeleteAllTasks = viewModel::onDeleteAllTasks
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToAddTask,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Icon(Icons.Rounded.Add, contentDescription = "Add Task")
            }
        }
    ) { paddingValues ->
        TasksContentComponent(
            paddingValues = paddingValues,
            selectedDay = selectedDay,
            isLocked = isLocked,
            tasks = tasks,
            uiState = uiState,
            currentDay = currentDay,
            onDaySelected = viewModel::onDaySelected,
            onTaskChecked = viewModel::onTaskChecked,
            onDeleteTask = viewModel::onDeleteTask
        )
    }
}