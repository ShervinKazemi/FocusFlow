package com.example.focusflow.presentation.feature.task.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.focusflow.domain.model.Task

@Composable
fun TaskContentComponent(
    task: Task,
    isLocked: Boolean,
    onTaskChecked: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isLocked) {
            Icon(
                imageVector = Icons.Outlined.Lock,
                modifier = Modifier
                    .padding(12.dp)
                    .size(24.dp),
                contentDescription = "Task locked"
            )
        } else {
            Checkbox(
                checked = task.isDone,
                onCheckedChange = onTaskChecked,
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary
                )
            )
        }

        Text(
            text = task.title,
            style = MaterialTheme.typography.titleMedium,
            color = if (task.isDone) MaterialTheme.colorScheme.outline
            else MaterialTheme.colorScheme.onSurface,
            textDecoration = if (task.isDone) TextDecoration.LineThrough
            else TextDecoration.None
        )
    }
}