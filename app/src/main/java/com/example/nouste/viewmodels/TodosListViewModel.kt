package com.example.nouste.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.nouste.data.db.NotesDatabase
import com.example.nouste.data.repository.NotesRepository

class TodosListViewModel(application: Application): AndroidViewModel(application) {

    private val notesRepository: NotesRepository

    init {
        val noteDao = NotesDatabase.getDatabase(application).noteDao()
        notesRepository = NotesRepository(noteDao = noteDao)
    }

    fun getTodos(noteId: Int) = notesRepository.getTodos(noteId = noteId)

}