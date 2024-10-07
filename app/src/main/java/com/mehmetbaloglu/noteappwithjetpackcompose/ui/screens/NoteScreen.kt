package com.mehmetbaloglu.noteappwithjetpackcompose.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mehmetbaloglu.noteappwithjetpackcompose.R
import com.mehmetbaloglu.noteappwithjetpackcompose.data.model.Note
import com.mehmetbaloglu.noteappwithjetpackcompose.utils.Utils


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit = {},
    onEditNote: (Note) -> Unit = {},
    onRemoveNote: (Note) -> Unit = {}
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    //val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Column(modifier = Modifier) {
        CreateTopAppBar()
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
                        onAddNote(
                            Note(
                                id = 0,
                                title = title,
                                description = description,
                                entryDate = Utils.getCurrentDateTime()
                            )
                        )
                        title = ""
                        description = ""
                        Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            context, "Please enter title and description", Toast.LENGTH_SHORT
                        ).show()
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
                NoteCard(it, onRemoveNote = onRemoveNote, onEditNote = onEditNote)
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun CreateTopAppBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        actions = {
            Icon(
                imageVector = Icons.Rounded.Create,
                contentDescription = "Icon_Notification",
                modifier = Modifier
                    .padding(6.dp)
                    .size(30.dp)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xAAAAAAAA), titleContentColor = Color(0xFFFFFFFF)
        ),
    )
}

@Composable
fun NoteCard(
    note: Note,
    onRemoveNote: (Note) -> Unit = {},
    onEditNote: (Note) -> Unit = {}
) {
    var showDeleteConfirmation by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable { showEditDialog = true },
        shape = RoundedCornerShape(4.dp, 44.dp, 4.dp, 44.dp),
        //color = Color(0xAAFF6500),
        contentColor = Color(0xFF1E3E62),
        border = BorderStroke(1.dp, Color(0xFF0B192C))
    ) {
        Column(modifier = Modifier.clickable { showEditDialog = true }) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Icon(imageVector = Icons.Rounded.Delete,
                    contentDescription = "Icon_Notification",
                    modifier = Modifier
                        .padding(2.dp)
                        .clickable { showDeleteConfirmation = true })
                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = note.title,
                    modifier = Modifier.padding(2.dp, 5.dp, 2.dp, 0.dp),
                    fontSize = 20.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )

            }
            HorizontalDivider(modifier = Modifier.padding(5.dp, 0.dp, 15.dp, 2.dp))
            Text(text = note.description, modifier = Modifier.padding(5.dp))
            HorizontalDivider(modifier = Modifier.padding(5.dp, 0.dp, 15.dp, 2.dp))
            Text(
                text = note.entryDate, modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.End)
            )
        }
    }
    //Delete note confirmation
    DeleteConfirmationBottomSheet(showSheet = showDeleteConfirmation,
        onDismiss = { showDeleteConfirmation = false },
        onConfirm = {
            onRemoveNote(note)
            showDeleteConfirmation = false
        })
    // Edit note dialog
    EditNoteDialog(
        showDialog = showEditDialog,
        onDismiss = { showEditDialog = false },
        onConfirm = { updatedNote ->
            onEditNote(updatedNote)
            showEditDialog = false
        },
        note = note
    )
}


@Composable
fun EditNoteDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (Note) -> Unit,
    note: Note
) {
    var title by remember { mutableStateOf(note.title) }
    var description by remember { mutableStateOf(note.description) }
    val context = LocalContext.current

    if (showDialog) {
        androidx.compose.ui.window.Dialog(onDismissRequest = onDismiss) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(16.dp),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Edit Note", fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Title") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Description") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Row {
                        Button(
                            onClick = {
                                if (title.isNotEmpty() && description.isNotEmpty()) {
                                    val updatedNote = note.copy(
                                        title = title,
                                        description = description,
                                        entryDate = Utils.getCurrentDateTime()
                                    )
                                    onConfirm(updatedNote)
                                    Toast.makeText(context, "Note Updated", Toast.LENGTH_SHORT)
                                        .show()

                                } else {
                                    Toast.makeText(
                                        context,
                                        "Please enter title and description",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            }, modifier = Modifier.padding(8.dp)
                        ) {
                            Text(text = "Save")
                        }
                        Button(
                            onClick = onDismiss,
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(text = "Cancel")
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteConfirmationBottomSheet(
    showSheet: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit
) {
    val context = LocalContext.current

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismiss
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Are you sure you want to delete this note?", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Button(
                        //onClick = onConfirm,
                        onClick = {
                            onConfirm()
                            Toast.makeText(context, "Note Deleted", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Yes")
                    }
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "No")
                    }
                }
            }
        }
    }
}


