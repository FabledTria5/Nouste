package com.example.nouste.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.nouste.data.db.NotesDatabase
import com.example.nouste.data.repository.NotesRepository
import com.example.nouste.enums.Gradients
import kotlinx.coroutines.runBlocking

class NoteEditViewModel(application: Application) : AndroidViewModel(application) {

    private val notesRepository: NotesRepository

    init {
        val noteDao = NotesDatabase.getDatabase(application).noteDao()
        notesRepository = NotesRepository(noteDao = noteDao)
    }

    var currentGradient: Gradients = Gradients.ORANGE

    fun getNote(noteId: Int) = runBlocking { notesRepository.getNote(noteId = noteId) }

}