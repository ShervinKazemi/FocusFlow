package com.example.focusflow

import android.app.Application
import com.example.focusflow.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FocusFlowApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(
            appDeclaration = {
                androidContext(this@FocusFlowApp)
                modules(appModule)
            }
        )

    }
}