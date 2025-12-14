package com.blannonnetwork.mynotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blannonnetwork.mynotes.db.NoteDataBase
import com.blannonnetwork.mynotes.model.Note
import kotlinx.coroutines.launch

class HomeViewModel (noteDatabase: NoteDataBase) : ViewModel(){

    private val dao = noteDatabase.noteDao()
    private val _notes = dao.getAllNotes()
    val notes = _notes

    fun addNotes(note: Note){
        viewModelScope.launch {
            dao.insertNote(note)
        }
    }

}