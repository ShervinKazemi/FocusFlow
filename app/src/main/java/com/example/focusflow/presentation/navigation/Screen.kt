package com.example.focusflow.presentation.navigation

sealed class Screen(val route: String) {
    object Task: Screen("taskScreen")
    object Add: Screen("addScreen")
}