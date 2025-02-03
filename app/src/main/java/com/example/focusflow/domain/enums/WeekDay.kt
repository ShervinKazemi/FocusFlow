package com.example.focusflow.domain.enums

enum class WeekDay {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

    val shortName: String
        get() = name.take(3)
}