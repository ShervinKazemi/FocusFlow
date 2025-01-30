package com.example.focusflow.presentation.feature.add.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddTaskButtonComponent(
    title: String,
    onButtonClick:() -> Unit
) {

    Button(
        onClick = onButtonClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(bottom = 16.dp),
        enabled = title.isNotBlank(),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            "Add Task",
            style = MaterialTheme.typography.titleMedium
        )
    }

}

