@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.focusflow.presentation.feature.task.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.focusflow.domain.model.Task

@Composable
fun TaskItemComponent(
    task: Task,
    currentDay: String,
    isLocked: Boolean,
    onTaskChecked: (Int, Boolean, String) -> Unit,
    onDeleteTask: (Task) -> Unit
) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { dismissValue ->
            when (dismissValue) {
                SwipeToDismissBoxValue.EndToStart -> {
                    showDeleteDialog = true
                    true
                }
                else -> false
            }
        },
        positionalThreshold = { it * 0.5f }
    )

    LaunchedEffect(dismissState.currentValue) {
        if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart) {
            dismissState.reset()
        }
    }

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = { DismissBackgroundComponent(dismissState) },
        content = {
            val scale by animateFloatAsState(
                targetValue = if (dismissState.dismissDirection == SwipeToDismissBoxValue.EndToStart)
                    0.95f else 1f,
                label = "ScaleAnimation"
            )

            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer(scaleX = scale, scaleY = scale),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
            ) {
                TaskContentComponent(
                    task = task,
                    isLocked = isLocked,
                    onTaskChecked = { isChecked ->
                        onTaskChecked(task.id, isChecked, currentDay)
                    }
                )
            }
        }
    )

    if (showDeleteDialog) {
        DeleteTaskDialogComponent(
            onDismiss = { showDeleteDialog = false },
            onConfirm = { onDeleteTask(task) }
        )
    }
}