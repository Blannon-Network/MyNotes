package com.blannonnetwork.mynotes.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.blannonnetwork.mynotes.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAllNotes(): Flow<List<Note>>

    @Insert
    suspend fun insertNote(note: Note)
}