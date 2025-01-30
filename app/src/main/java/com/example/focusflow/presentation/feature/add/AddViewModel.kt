package com.example.focusflow.presentation.feature.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.focusflow.domain.model.Task
import com.example.focusflow.domain.usecase.AddTaskUseCase
import com.example.focusflow.util.coroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AddViewModel(
    private val addTaskUseCase: AddTaskUseCase,
):ViewModel() {
    val title = MutableStateFlow("")

    fun addTask(task: Task) {
        viewModelScope.launch(coroutineExceptionHandler) {
            addTaskUseCase(task)
        }
    }

}