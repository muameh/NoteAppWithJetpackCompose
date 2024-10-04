package com.mehmetbaloglu.noteappwithjetpackcompose.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mehmetbaloglu.noteappwithjetpackcompose.data.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDBDao {

    @Query("SELECT * from notes_tbl")
    fun getNotes(): Flow<List<Note>>

    @Delete
    suspend fun deleteNote(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Query("DELETE from notes_tbl")
    suspend fun deleteAll()

    @Query("SELECT * from notes_tbl where id =:id")
    suspend fun getNoteById(id: String): Note

    @Update
    suspend fun updateNote(note: Note)





}