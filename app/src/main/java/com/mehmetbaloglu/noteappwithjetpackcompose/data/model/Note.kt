package com.mehmetbaloglu.noteappwithjetpackcompose.data.model

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.UUID

data class Note(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    val entryDate: Long = Calendar.getInstance().timeInMillis
) {
    // Function to format the entryDate into "DD/MM/YYYY"
    fun getFormattedDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(entryDate)
    }
}
