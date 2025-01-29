package com.example.focusflow.di

import androidx.room.Room
import com.example.focusflow.data.local.database.AppDatabase
import com.example.focusflow.data.repository.TaskRepositoryImpl
import com.example.focusflow.domain.repository.TaskRepository
import com.example.focusflow.domain.usecase.AddTaskUseCase
import com.example.focusflow.domain.usecase.DeleteTaskUseCase
import com.example.focusflow.domain.usecase.GetAllTasksUseCase
import com.example.focusflow.domain.usecase.GetTasksByRepeatDayUseCase
import com.example.focusflow.domain.usecase.UpdateTaskStatusUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    // RoomDatabase will be added here
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "focus_flow.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().taskDao() }

    // Repositories will be added here
    single<TaskRepository> { TaskRepositoryImpl(get()) }

    // useCase will be added here
    single { GetAllTasksUseCase(get()) }
    single { GetTasksByRepeatDayUseCase(get()) }
    single { AddTaskUseCase(get()) }
    single { DeleteTaskUseCase(get()) }
    single { UpdateTaskStatusUseCase(get()) }

}