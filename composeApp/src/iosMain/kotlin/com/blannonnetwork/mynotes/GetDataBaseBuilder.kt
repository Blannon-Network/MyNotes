package com.blannonnetwork.mynotes



import androidx.room.Room
import androidx.room.RoomDatabase
import com.blannonnetwork.mynotes.db.NoteDataBase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

fun getDataBaseBuilder(): RoomDatabase.Builder<NoteDataBase>{

    val dbPath = getDocumentPath() + "note_database.db"
    return Room.databaseBuilder(name = dbPath)
}

@OptIn(ExperimentalForeignApi::class)
fun getDocumentPath(): String{

    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null
    )

    return requireNotNull(documentDirectory?.path)
}