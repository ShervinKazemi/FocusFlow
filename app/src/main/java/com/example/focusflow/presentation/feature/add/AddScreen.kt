package com.example.focusflow.presentation.feature.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.focusflow.data.local.converter.TaskConverters
import com.example.focusflow.domain.enums.WeekDay
import com.example.focusflow.domain.model.Task
import com.example.focusflow.presentation.feature.add.component.AddTaskButtonComponent
import com.example.focusflow.presentation.feature.add.component.AddTaskFieldComponent
import com.example.focusflow.presentation.feature.add.component.SelectRepeatDaysComponent
import com.example.focusflow.presentation.feature.add.component.TopAppBarComponent
import dev.burnoo.cokoin.navigation.getNavViewModel

@Composable
fun AddTaskScreen(
    viewModel: AddViewModel = getNavViewModel<AddViewModel>(),
    onNavigateBack: () -> Unit,
) {
    val title by viewModel.title.collectAsState()
    var selectedDays by remember { mutableStateOf<Set<WeekDay>>(emptySet()) }
    var showDaysPicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {

        TopAppBarComponent(onNavigateBack = onNavigateBack)

        AddTaskFieldComponent(
            title = title,
            hint = "Title",
            onValueChange = { viewModel.title.value = it })

        SelectRepeatDaysComponent(
            showDaysPicker = showDaysPicker,
            selectedDays = selectedDays,
            onClickCard = { showDaysPicker = !showDaysPicker },
            onDaySelected = { newSelectedDays -> selectedDays = newSelectedDays }
        )

        Spacer(modifier = Modifier.weight(1f))

        AddTaskButtonComponent(title = title) {
            if (title.isNotBlank()) {
                viewModel.addTask(
                    Task(
                        title = title,
                        repeatDays = selectedDays.toList(),
                        dateCreated = TaskConverters.getCurrentDateString(),
                        timeCreated = TaskConverters.getCurrentTimeString()
                    )
                )
                onNavigateBack()
            }
        }
    }
}