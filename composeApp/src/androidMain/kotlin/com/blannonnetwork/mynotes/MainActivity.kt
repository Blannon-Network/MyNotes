package com.blannonnetwork.mynotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.blannonnetwork.mynotes.db.getNoteDataBase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App(
                dataBase = getNoteDataBase(getDataBaseBuilder(this@MainActivity))
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    val database = getNoteDataBase(getDataBaseBuilder(LocalContext.current))
    App(
        database
    )
}