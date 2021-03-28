package com.example.nouste.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.nouste.data.db.NotesDatabase
import com.example.nouste.data.repository.NotesRepository
import com.example.nouste.data.tables.Note
import com.example.nouste.data.tables.ToDo
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val notesRepository: NotesRepository

    init {
        val noteDao = NotesDatabase.getDatabase(application).noteDao()
        notesRepository = NotesRepository(noteDao = noteDao)
    }

    fun saveNote(note: Note?, todosData: List<ToDo>?, ) = viewModelScope.launch {
        if (!todosData.isNullOrEmpty()) {
            notesRepository.saveNote(note, todosData)
        }
    }
}