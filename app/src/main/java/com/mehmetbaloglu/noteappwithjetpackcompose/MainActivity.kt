package com.mehmetbaloglu.noteappwithjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mehmetbaloglu.noteappwithjetpackcompose.ui.screens.NoteScreen
import com.mehmetbaloglu.noteappwithjetpackcompose.ui.theme.NoteAppWithJetPackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteAppWithJetPackComposeTheme {
                NoteScreen()
            }
        }
    }
}

