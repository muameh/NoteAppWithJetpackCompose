package com.mehmetbaloglu.noteappwithjetpackcompose.data.repository

import com.mehmetbaloglu.noteappwithjetpackcompose.data.model.Note
import com.mehmetbaloglu.noteappwithjetpackcompose.room.NoteDB
import com.mehmetbaloglu.noteappwithjetpackcompose.room.NoteDBDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDBDao: NoteDBDao) {
    suspend fun addNote(note: Note) = noteDBDao.insertNote(note)
    suspend fun updateNote(note: Note) = noteDBDao.updateNote(note)
    suspend fun deleteNote(note: Note) = noteDBDao.deleteNote(note)
    suspend fun deleteAllNotes() = noteDBDao.deleteAll()

    fun getAllNotes(): Flow<List<Note>> = noteDBDao.getNotes().flowOn(Dispatchers.IO).conflate()

}