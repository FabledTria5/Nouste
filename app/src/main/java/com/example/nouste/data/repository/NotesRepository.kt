package com.example.nouste.data.repository

import com.example.nouste.data.dao.NoteDao
import com.example.nouste.data.relations.NoteWithToDos
import com.example.nouste.data.tables.Note
import com.example.nouste.data.tables.ToDo

class NotesRepository(private val noteDao: NoteDao) {

    suspend fun saveNote(note: Note?, todosData: List<ToDo>) {
        // Note is always not null
        noteDao.insertNote(note = note!!)
        noteDao.connectTodosToNote(note = note, todos = todosData)
    }

    fun getNotes() = noteDao.getNotesWithTodos()

    suspend fun deleteNote(noteWithToDos: NoteWithToDos) {
        noteDao.deleteNoteWithTodos(noteWithToDos = noteWithToDos)
    }

}