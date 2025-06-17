package com.example.remiderapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.remiderapp.R
import com.example.remiderapp.data.Reminder

class ReminderAdapter(
    private var reminders: List<Reminder>,
    private val onEdit: (Reminder) -> Unit,
    private val onDelete: (Reminder) -> Unit
) : RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder>() {

    inner class ReminderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val tvDateTime: TextView = itemView.findViewById(R.id.tvDateTime)
        val btnEdit: ImageButton = itemView.findViewById(R.id.btnEdit)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reminder, parent, false)
        return ReminderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        val reminder = reminders[position]
        holder.tvTitle.text = reminder.title
        
        if (!reminder.description.isNullOrEmpty()) {
            holder.tvDescription.text = reminder.description
            holder.tvDescription.visibility = View.VISIBLE
        } else {
            holder.tvDescription.visibility = View.GONE
        }

        if (!reminder.dateTime.isNullOrEmpty()) {
            holder.tvDateTime.text = reminder.dateTime
            holder.tvDateTime.visibility = View.VISIBLE
        } else {
            holder.tvDateTime.visibility = View.GONE
        }

        holder.btnEdit.setOnClickListener { onEdit(reminder) }
        holder.btnDelete.setOnClickListener { onDelete(reminder) }
    }

    override fun getItemCount() = reminders.size

    fun setReminders(newReminders: List<Reminder>) {
        reminders = newReminders
        notifyDataSetChanged()
    }
} 