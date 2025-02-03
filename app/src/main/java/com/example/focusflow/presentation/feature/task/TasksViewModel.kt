@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.focusflow.presentation.feature.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.focusflow.data.local.converter.TaskConverters
import com.example.focusflow.domain.enums.WeekDay
import com.example.focusflow.domain.model.Task
import com.example.focusflow.domain.usecase.DeleteAllTasksUseCase
import com.example.focusflow.domain.usecase.DeleteTaskUseCase
import com.example.focusflow.domain.usecase.GetTasksForDayUseCase
import com.example.focusflow.domain.usecase.UpdateTaskStatusUseCase
import com.example.focusflow.util.coroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class TasksViewModel(
    private val getTasksForDayUseCase: GetTasksForDayUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val deleteAllTasksUseCase: DeleteAllTasksUseCase,
    private val updateTaskStatusUseCase: UpdateTaskStatusUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TasksUiState())
    val uiState = _uiState.asStateFlow()

    private val _currentDay = MutableStateFlow(TaskConverters.getCurrentDay())
    val currentDay: StateFlow<String> = _currentDay

    private val _selectedDay = MutableStateFlow(WeekDay.MONDAY)
    val selectedDay = _selectedDay.asStateFlow()

    init {
        viewModelScope.launch {
            val current = TaskConverters.getCurrentDay().uppercase()

            val matchingDay = WeekDay.entries.find { it.name == current }

            matchingDay?.let {
                _selectedDay.value = it
            }
        }
    }

    private val _taskUpdateTrigger = MutableStateFlow(0)

    val tasks = _selectedDay
        .flatMapLatest { day ->
            _taskUpdateTrigger.flatMapLatest {
                flow {
                    _uiState.update { it.copy(isLoading = true) }
                    try {
                        val fetchedTasks = getTasksForDayUseCase(day.name)
                        emit(fetchedTasks)
                        _uiState.update { it.copy(isLoading = false, error = null) }
                    } catch (e: Exception) {
                        _uiState.update { it.copy(error = e.message, isLoading = false) }
                        emit(emptyList())
                    }
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun onDaySelected(day: WeekDay) {
        _selectedDay.value = day
    }

    fun onTaskChecked(taskId: Int, isDone: Boolean, day: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            updateTaskStatusUseCase(taskId, isDone, day)
            _taskUpdateTrigger.value += 1
        }
    }

    fun onDeleteTask(task: Task) {
        viewModelScope.launch(coroutineExceptionHandler) {
            deleteTaskUseCase(task)
            _taskUpdateTrigger.value += 1
        }
    }

    fun onDeleteAllTasks() {
        viewModelScope.launch(coroutineExceptionHandler) {
            deleteAllTasksUseCase()
            _taskUpdateTrigger.value += 1
        }
    }

    fun isFutureDay(selectedDay: WeekDay , currentDay: WeekDay): Boolean {
        val days = WeekDay.entries.toTypedArray()
        val selectedIndex = days.indexOf(selectedDay)
        val currentIndex = days.indexOf(currentDay)

        return selectedIndex > currentIndex

    }

}

data class TasksUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
)