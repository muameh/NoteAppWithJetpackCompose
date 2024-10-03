package com.mehmetbaloglu.noteappwithjetpackcompose.data.dummy_data

import androidx.collection.objectListOf
import com.mehmetbaloglu.noteappwithjetpackcompose.data.model.Note

class NotesDataSource {
    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "Grocery List", description = "Buy milk, eggs, bread, and fruits."),
            Note(title = "Meeting Notes", description = "Discuss project timelines and deliverables with the team."),
            Note(title = "Birthday Party", description = "Plan the surprise party for Sarah on June 15th."),
            Note(title = "Book Recommendations", description = "1. The Great Gatsby 2. To Kill a Mockingbird 3. 1984"),
            Note(title = "Travel Plans", description = "Book flights to Paris for vacation next month."),
            Note(title = "Workout Routine", description = "1. Cardio 3x a week 2. Weight training 2x a week."),
            Note(title = "Weekend Projects", description = "Fix the leaky faucet and paint the living room."),
            Note(title = "Movie Watchlist", description = "1. Inception 2. The Matrix 3. Interstellar"),
            Note(title = "Learn Kotlin", description = "Complete the online Kotlin course by the end of the month."),
            Note(title = "Daily Journal", description = "Reflect on the day and write down my thoughts and feelings.")
        )
    }
}