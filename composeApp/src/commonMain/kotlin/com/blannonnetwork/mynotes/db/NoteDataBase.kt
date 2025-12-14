package com.blannonnetwork.mynotes.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.blannonnetwork.mynotes.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(entities = [Note::class], version = 1, exportSchema = true)
@ConstructedBy(NoteDatabaseConstructor::class)
abstract class NoteDataBase : RoomDatabase(){

    abstract fun noteDao(): NoteDao
}

@Suppress("KotlinNoActualForExpect")
expect object NoteDatabaseConstructor: RoomDatabaseConstructor<NoteDataBase>{
    override fun initialize(): NoteDataBase
}

fun getNoteDataBase(builder: RoomDatabase.Builder<NoteDataBase>): NoteDataBase{

    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}