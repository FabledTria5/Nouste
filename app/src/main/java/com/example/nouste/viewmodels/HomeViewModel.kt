package com.example.nouste.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.nouste.data.db.NotesDatabase
import com.example.nouste.data.relations.NoteWithToDos
import com.example.nouste.data.repository.NotesRepository
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val notesRepository: NotesRepository
    var notes: LiveData<List<NoteWithToDos>>

    init {
        val notesDao = NotesDatabase.getDatabase(application).noteDao()
        notesRepository = NotesRepository(noteDao = notesDao)
        notes = notesRepository.getNotes()
    }

    fun getDate(): String = runBlocking {
        val dateFormatter = SimpleDateFormat("EEEE", Locale.getDefault())
        val monthFormatter = SimpleDateFormat("LLLL", Locale.getDefault())

        "${dateFormatter.format(Date())} ${
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        }, ${monthFormatter.format(Date())}"
    }
}

