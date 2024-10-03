package com.mehmetbaloglu.noteappwithjetpackcompose.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mehmetbaloglu.noteappwithjetpackcompose.R
import com.mehmetbaloglu.noteappwithjetpackcompose.data.dummy_data.NotesDataSource
import com.mehmetbaloglu.noteappwithjetpackcompose.data.model.Note


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(notes: List<Note>, onAddNote: (Note) -> Unit, onRemoveNote: (Note) -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Column(modifier = Modifier.clickable { focusManager.clearFocus() }) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Icon_Notification"
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xAAAAAAAA), titleContentColor = Color(0xFFFFFFFF)
            ),
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Title") },
                singleLine = true,
                modifier = Modifier.padding(top = 10.dp)
            )
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(text = "Add a note") },
                singleLine = true,
                modifier = Modifier.padding(top = 10.dp)
            )
            Button(
                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        onAddNote(Note(title = title, description = description))
                        title = ""
                        description = ""
                        Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                    }
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFFFFF),
                    contentColor = Color(0xFF1E3E62),
                    disabledContainerColor = Color(0xFF0B192C),
                    disabledContentColor = Color(0xFF1E3E62)
                ),
                modifier = Modifier.padding(top = 10.dp),
                border = BorderStroke(1.dp, Color(0xFF1E3E62))
            ) {
                Text(
                    text = "Save", modifier = Modifier.padding(horizontal = 10.dp), fontSize = 20.sp
                )
            }
        }
        HorizontalDivider(modifier = Modifier.padding(20.dp))
        LazyColumn {
            items(notes) {
                NoteCard(it, onNoteClicked = {}, onRemoveNote = onRemoveNote)
            }
        }
    }

}

@Composable
fun NoteCard(
    note: Note,
    onNoteClicked: (Note) -> Unit = {},
    onRemoveNote: (Note) -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(4.dp, 44.dp, 4.dp, 44.dp),
        //color = Color(0xAAFF6500),
        contentColor = Color(0xFF1E3E62),
        border = BorderStroke(1.dp, Color(0xFF0B192C))
    ) {
        Column(modifier = Modifier.clickable { }) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "Icon_Notification",
                    modifier = Modifier
                        .padding(2.dp)
                        .clickable { onRemoveNote(note) }
                )
                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = note.title,
                    modifier = Modifier.padding(2.dp, 5.dp, 2.dp, 0.dp),
                    fontSize = 20.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )

            }

            HorizontalDivider(modifier = Modifier.padding(5.dp, 0.dp, 15.dp, 2.dp))
            Text(
                text = note.description,
                modifier = Modifier.padding(5.dp)
            )
            HorizontalDivider(modifier = Modifier.padding(5.dp, 0.dp, 15.dp, 2.dp))
            Text(
                text = note.getFormattedDate(),
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.End)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}