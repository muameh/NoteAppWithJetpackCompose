package com.mehmetbaloglu.noteappwithjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mehmetbaloglu.noteappwithjetpackcompose.data.model.Note
import com.mehmetbaloglu.noteappwithjetpackcompose.ui.screens.NoteScreen
import com.mehmetbaloglu.noteappwithjetpackcompose.ui.theme.NoteAppWithJetPackComposeTheme
import com.mehmetbaloglu.noteappwithjetpackcompose.ui.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            NoteAppWithJetPackComposeTheme {
                //val noteViewModel = viewModel<NoteViewModel>()
                val noteViewModel: NoteViewModel = viewModel()
                NotesApp(noteViewModel)
            }
        }
    }
}

@Composable
fun NotesApp(noteViewModel: NoteViewModel){
    val noteList = noteViewModel.noteList.collectAsState().value
    NoteScreen(
        notes = noteList,
        onAddNote = {noteViewModel.addNote(it)},
        onRemoveNote = {noteViewModel.deleteNote(it)},
        onEditNote = {noteViewModel.updateNote(it)}
    )
}

