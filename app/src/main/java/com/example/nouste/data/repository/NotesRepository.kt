package com.example.nouste.data.repository

import com.example.nouste.data.dao.NoteDao
import com.example.nouste.data.relations.NoteWithToDos
import com.example.nouste.data.tables.Note
import com.example.nouste.data.tables.ToDo

class NotesRepository(private val noteDao: NoteDao) {

    suspend fun saveNote(note: Note?, todosData: List<ToDo>) {
        if (note != null) {
            noteDao.insertNote(note = note)
            noteDao.connectTodosToNote(note = note, todos = todosData)
        }
    }

    suspend fun updateNote(note: Note?, todosData: List<ToDo>) {
        if (note != null) {
            noteDao.updateNote(
                note.id,
                note.noteTitle,
                note.noteText,
                note.noteImage,
                note.noteGradient
            )
        }
        noteDao.updateTodos(todosData = todosData)
    }

    suspend fun deleteNote(noteWithToDos: NoteWithToDos) =
        noteDao.deleteNoteWithTodos(noteWithToDos = noteWithToDos)

    suspend fun getNote(noteId: Int) = noteDao.getNote(noteId = noteId)

    suspend fun getTodos(noteId: Int) = noteDao.getTodos(noteId = noteId)

    fun getNotes() = noteDao.getNotesWithTodos()

}