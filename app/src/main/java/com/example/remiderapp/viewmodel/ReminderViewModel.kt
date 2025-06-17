package com.example.remiderapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.remiderapp.data.Reminder
import com.example.remiderapp.data.ReminderDatabase
import com.example.remiderapp.repository.ReminderRepository
import kotlinx.coroutines.launch

class ReminderViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ReminderRepository
    val allReminders: LiveData<List<Reminder>>

    init {
        val dao = ReminderDatabase.getDatabase(application).reminderDao()
        repository = ReminderRepository(dao)
        allReminders = repository.allReminders
    }

    fun insert(reminder: Reminder) = viewModelScope.launch {
        repository.insert(reminder)
    }

    fun update(reminder: Reminder) = viewModelScope.launch {
        repository.update(reminder)
    }

    fun delete(reminder: Reminder) = viewModelScope.launch {
        repository.delete(reminder)
    }
} 