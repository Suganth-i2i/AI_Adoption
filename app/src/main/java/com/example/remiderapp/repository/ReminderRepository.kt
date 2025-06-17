package com.example.remiderapp.repository

import androidx.lifecycle.LiveData
import com.example.remiderapp.data.Reminder
import com.example.remiderapp.data.ReminderDao

class ReminderRepository(private val reminderDao: ReminderDao) {
    val allReminders: LiveData<List<Reminder>> = reminderDao.getAllReminders()

    suspend fun insert(reminder: Reminder) = reminderDao.insert(reminder)
    suspend fun update(reminder: Reminder) = reminderDao.update(reminder)
    suspend fun delete(reminder: Reminder) = reminderDao.delete(reminder)
} 