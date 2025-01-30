package com.example.focusflow.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.focusflow.presentation.feature.add.AddTaskScreen
import com.example.focusflow.presentation.feature.task.TaskScreen
import dev.burnoo.cokoin.navigation.KoinNavHost

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    KoinNavHost(navController = navController, startDestination = Screen.Task.route) {
        composable(route = Screen.Task.route) {
            TaskScreen()
        }
        composable(route = Screen.Add.route) {
            AddTaskScreen() {
                navController.popBackStack()
            }
        }
    }
}