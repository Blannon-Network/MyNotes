package com.blannonnetwork.mynotes.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blannonnetwork.mynotes.model.Note
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ListNotesScreen(list: List<Note>){
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(list){
            NoteItem(note = it)
        }
    }
}

fun getRandomColor(): Color {
    val random = (150..255).random()
    return Color(random, random, random)
}

@Composable
fun NoteItem(note: Note){
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(getRandomColor())
            .padding(8.dp)

    ){
        Text(
            text = note.title,
            modifier = Modifier
                .padding(8.dp),
            fontSize = 20.sp,
            color = Color.Black
        )
    }
}

@Composable
@Preview
fun PreviewList(){
    ListNotesScreen(listOf(
        Note("Hola", "Description"),
        Note("Hola", "Description"),

        Note("hapo tu", "Description")
    )
    )
}