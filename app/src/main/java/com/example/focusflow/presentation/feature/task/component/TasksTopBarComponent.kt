@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.focusflow.presentation.feature.task.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun TasksTopAppBarComponent(
    onDeleteAllTasks: () -> Unit,
) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text("Tasks") },
        actions = {
            IconButton(onClick = { showDeleteDialog = true }) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "Delete all tasks"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )

    if (showDeleteDialog) {
        DeleteTasksDialogComponent(
            onDismiss = { showDeleteDialog = false },
            onConfirm = onDeleteAllTasks
        )
    }
}