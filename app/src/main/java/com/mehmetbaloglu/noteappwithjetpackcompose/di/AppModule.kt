package com.mehmetbaloglu.noteappwithjetpackcompose.di

import android.content.Context
import androidx.room.Room
import com.mehmetbaloglu.noteappwithjetpackcompose.room.NoteDB
import com.mehmetbaloglu.noteappwithjetpackcompose.room.NoteDBDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideNotesDao(notesDatabase: NoteDB): NoteDBDao = notesDatabase.noteDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NoteDB =
        Room.databaseBuilder(
        context,
        NoteDB::class.java,
        "notes_db")
        .fallbackToDestructiveMigration()
        .build()

}