package com.blannonnetwork.mynotes

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.blannonnetwork.mynotes.db.NoteDataBase

fun getDataBaseBuilder(context: Context): RoomDatabase.Builder<NoteDataBase>{
    val appContext = context.applicationContext
    val dbPath = appContext.getDatabasePath("note_database.db")
    return Room.databaseBuilder(appContext, NoteDataBase::class.java, dbPath.path)
}