package com.example.remiderapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.remiderapp.data.Reminder
import com.example.remiderapp.databinding.ActivityMainBinding
import com.example.remiderapp.ui.AddEditReminderDialog
import com.example.remiderapp.ui.ReminderAdapter
import com.example.remiderapp.viewmodel.ReminderViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ReminderViewModel by viewModels()
    private lateinit var adapter: ReminderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupObservers()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        adapter = ReminderAdapter(
            reminders = emptyList(),
            onEdit = { reminder -> showAddEditDialog(reminder) },
            onDelete = { reminder -> deleteReminder(reminder) }
        )
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    private fun setupObservers() {
        viewModel.allReminders.observe(this, Observer { reminders ->
            adapter.setReminders(reminders)
            binding.emptyView.visibility = if (reminders.isEmpty()) View.VISIBLE else View.GONE
        })
    }

    private fun setupClickListeners() {
        binding.fabAdd.setOnClickListener {
            showAddEditDialog(null)
        }
    }

    private fun showAddEditDialog(reminder: Reminder?) {
        AddEditReminderDialog(this, reminder) { newReminder ->
            if (reminder == null) {
                viewModel.insert(newReminder)
                Toast.makeText(this, "Reminder added", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.update(newReminder)
                Toast.makeText(this, "Reminder updated", Toast.LENGTH_SHORT).show()
            }
        }.show()
    }

    private fun deleteReminder(reminder: Reminder) {
        viewModel.delete(reminder)
        Toast.makeText(this, "Reminder deleted", Toast.LENGTH_SHORT).show()
    }
}