package com.example.remiderapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ReminderDao {
    @Query("SELECT * FROM reminders ORDER BY id DESC")
    fun getAllReminders(): LiveData<List<Reminder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reminder: Reminder)

    @Update
    suspend fun update(reminder: Reminder)

    @Delete
    suspend fun delete(reminder: Reminder)
} 