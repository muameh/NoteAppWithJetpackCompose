package com.mehmetbaloglu.noteappwithjetpackcompose.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mehmetbaloglu.noteappwithjetpackcompose.data.model.Note


@Database(entities = [Note::class], version = 2, exportSchema = false)
abstract class NoteDB : RoomDatabase() {

    abstract fun noteDao(): NoteDBDao
}