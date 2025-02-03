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
import com.example.focusflow.util.toWeekDay

@Composable
fun DaySelectorComponent(
    selectedDay: WeekDay,
    currentDay: String,
    onDaySelected: (WeekDay) -> Unit,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        WeekDay.entries.forEach { day ->
            DayChipComponent(
                day = day,
                isSelected = selectedDay == day,
                isCurrentDay = day == currentDay.uppercase().toWeekDay(),
                onSelected = { onDaySelected(day) }
            )
        }
    }
}
