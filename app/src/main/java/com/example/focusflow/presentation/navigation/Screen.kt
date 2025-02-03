package com.example.focusflow.presentation.navigation

sealed class Screen(val route: String) {
    data object Task: Screen("taskScreen")
    data object Add: Screen("addScreen")
}