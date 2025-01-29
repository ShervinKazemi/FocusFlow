package com.example.focusflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.focusflow.di.appModule
import com.example.focusflow.presentation.theme.AppTheme
import dev.burnoo.cokoin.Koin
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Koin(
                appDeclaration = {
                    androidContext(this@MainActivity)
                    modules(appModule)
                }
            ) {
                AppTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    }
                }
            }
        }
    }
}