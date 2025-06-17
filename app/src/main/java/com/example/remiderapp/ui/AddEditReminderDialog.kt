package com.example.remiderapp.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.example.remiderapp.R
import com.example.remiderapp.data.Reminder
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class AddEditReminderDialog(
    context: Context,
    private val reminder: Reminder?,
    private val onSave: (Reminder) -> Unit
) : Dialog(context) {

    private var selectedDate: Calendar = Calendar.getInstance()
    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val dateTimeFormatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_edit_reminder)

        // Set dialog width to 90% of screen width
        window?.let {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(it.attributes)
            layoutParams.width = (context.resources.displayMetrics.widthPixels * 0.9).toInt()
            it.attributes = layoutParams
        }

        val tvDialogTitle = findViewById<TextView>(R.id.tvDialogTitle)
        val etTitle = findViewById<TextInputEditText>(R.id.etTitle)
        val etDescription = findViewById<TextInputEditText>(R.id.etDescription)
        val etDateTime = findViewById<TextInputEditText>(R.id.etDateTime)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        // Set dialog title based on whether we're adding or editing
        tvDialogTitle?.text = if (reminder == null) "Add Reminder" else "Edit Reminder"

        if (reminder != null) {
            etTitle?.setText(reminder.title)
            etDescription?.setText(reminder.description)
            etDateTime?.setText(reminder.dateTime)
            
            // Parse existing date/time if available
            reminder.dateTime?.let { dateTimeStr ->
                try {
                    val date = dateTimeFormatter.parse(dateTimeStr)
                    date?.let {
                        selectedDate.time = it
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        // Set up date/time picker click listener
        etDateTime?.apply {
            isFocusable = false
            isClickable = true
            setOnClickListener {
                showDatePicker()
            }
        }

        btnSave?.setOnClickListener {
            val title = etTitle?.text?.toString()?.trim() ?: ""
            if (title.isEmpty()) {
                etTitle?.error = "Title is required"
                return@setOnClickListener
            }

            val description = etDescription?.text?.toString()?.trim()
            val dateTime = etDateTime?.text?.toString()?.trim()

            onSave(
                Reminder(
                    id = reminder?.id ?: 0,
                    title = title,
                    description = if (description.isNullOrEmpty()) null else description,
                    dateTime = if (dateTime.isNullOrEmpty()) null else dateTime
                )
            )
            dismiss()
        }

        btnCancel?.setOnClickListener {
            dismiss()
        }
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                selectedDate.set(Calendar.YEAR, year)
                selectedDate.set(Calendar.MONTH, month)
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                showTimePicker()
            },
            selectedDate.get(Calendar.YEAR),
            selectedDate.get(Calendar.MONTH),
            selectedDate.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val timePickerDialog = TimePickerDialog(
            context,
            { _, hourOfDay, minute ->
                selectedDate.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedDate.set(Calendar.MINUTE, minute)
                updateDateTimeField()
            },
            selectedDate.get(Calendar.HOUR_OF_DAY),
            selectedDate.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.show()
    }

    private fun updateDateTimeField() {
        findViewById<TextInputEditText>(R.id.etDateTime)?.setText(
            dateTimeFormatter.format(selectedDate.time)
        )
    }
} 