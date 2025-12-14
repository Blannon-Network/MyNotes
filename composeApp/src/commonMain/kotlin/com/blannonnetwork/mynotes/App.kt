package com.blannonnetwork.mynotes

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.blannonnetwork.mynotes.db.NoteDataBase
import com.blannonnetwork.mynotes.model.Note
import com.blannonnetwork.mynotes.notes.ListNotesScreen
import com.blannonnetwork.mynotes.notes.getRandomColor
import kotlinx.coroutines.launch
import mynotes.composeapp.generated.resources.Res
import mynotes.composeapp.generated.resources.note1
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App(dataBase: NoteDataBase) {
//    Starting point
    MyNotesTheme{
        val viewModel = viewModel { HomeViewModel(dataBase) }
        val bottomSheetState  = rememberModalBottomSheetState()
        var showBottomSheet by remember { mutableStateOf(false) }
        val coroutine = rememberCoroutineScope()

        Scaffold(
            floatingActionButton ={
                FloatingActionButton(
                    onClick = {
                        showBottomSheet = true
                    },
//                    shape = CircleShape
                ) {
                    Text(
                        text = "+",
                        fontSize = 18.sp
                    )

                }
            }
        ){
            val notes = viewModel.notes.collectAsStateWithLifecycle(emptyList())

            Column(
                modifier = Modifier.padding(it)
            ){
                Text(
                    "Notes",
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    fontSize = 30.sp
                )

                if (notes.value.isNotEmpty()){
                    ListNotesScreen(notes.value)
                } else {
                    EmptyView()
                }
            }

            if(showBottomSheet){
                ModalBottomSheet(onDismissRequest = {
                }, sheetState = bottomSheetState){
                    AddItemDialog(
                        onCancel = {
                            coroutine.launch {
                            bottomSheetState.hide()
                            }
                        },
                        onSave = {
                            viewModel.addNotes(it)
                            coroutine.launch {
                                bottomSheetState.hide()
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun AddItemDialog(onCancel: () -> Unit, onSave: (Note) -> Unit){
    var title by remember {mutableStateOf("")}
    var description by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .padding(16.dp)){
        val color = TextFieldDefaults.colors(
            focusedContainerColor = Transparent,
            unfocusedContainerColor = Transparent
        )

        TextField(
            value = title,
            onValueChange = {title = it},
            label = {Text("Title")},
            colors = color,
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(
                fontSize = 20.sp
            )
        )

        TextField(
            value = description,
            onValueChange = {description = it},
            label = {Text("Description")},
            colors = color,
            modifier = Modifier
                .fillMaxWidth(),
            minLines = 5
        )

        Row (modifier = Modifier.align(Alignment.End)){
            Text(
                "Cancel",
                Modifier.padding(8.dp).clickable {onCancel()}
            )
            Text(
                "Save",
                Modifier.padding(8.dp).clickable {onSave(Note(title, description))
                }
            )


        }
    }
}
@Composable
fun EmptyView(){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Image(
                painterResource(Res.drawable.note1),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
                    .size(200.dp)
            )

            Text(
                "Create your first note",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                fontSize = 20.sp
                
            )

        }
    }
}