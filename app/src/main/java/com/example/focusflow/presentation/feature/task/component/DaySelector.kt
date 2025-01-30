package com.example.focusflow.presentation.feature.task.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.focusflow.domain.enums.WeekDay

@Composable
fun DaySelector(
    selectedDay: WeekDay,
    onDaySelected: (WeekDay) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        WeekDay.entries.forEach { day ->
            DayChip(
                day = day,
                isSelected = selectedDay == day,
                onSelected = { onDaySelected(day) }
            )
        }
    }
}