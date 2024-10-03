package com.mehmetbaloglu.noteappwithjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.mehmetbaloglu.noteappwithjetpackcompose.data.dummy_data.NotesDataSource
import com.mehmetbaloglu.noteappwithjetpackcompose.data.model.Note
import com.mehmetbaloglu.noteappwithjetpackcompose.ui.screens.NoteScreen
import com.mehmetbaloglu.noteappwithjetpackcompose.ui.theme.NoteAppWithJetPackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val notes = remember {
                mutableStateListOf<Note>()
            }
            NoteAppWithJetPackComposeTheme {
                NoteScreen(
                    notes = notes,
                    onAddNote = {notes.add(it)},
                    onRemoveNote = {notes.remove(it)}
                )
            }
        }
    }
}

