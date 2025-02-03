package com.example.focusflow.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.focusflow.data.local.entity.TaskEntity


@Dao
interface TaskDao {


    @Query("SELECT * FROM tasks WHERE repeat_days LIKE '%' || :day || '%' OR repeat_days IS NULL ORDER BY is_done ASC, id ASC")
    suspend fun getTasksForDay(day: String): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Query("DELETE FROM tasks")
    suspend fun deleteAllTasks()

    @Query(
        "UPDATE tasks SET is_done = :isDone WHERE id = :taskId AND repeat_days LIKE '%' || :day " +
                "|| '%'"
    )
    suspend fun updateTaskStatus(taskId: Int, isDone: Boolean, day: String)

}