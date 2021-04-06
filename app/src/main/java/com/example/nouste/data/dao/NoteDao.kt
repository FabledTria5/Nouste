package com.example.nouste.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nouste.data.relations.NoteWithToDos
import com.example.nouste.data.tables.Note
import com.example.nouste.data.tables.ToDo

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTodos(todos: List<ToDo>)

    suspend fun connectTodosToNote(note: Note, todos: List<ToDo>) {
        for (todo in todos) {
            todo.noteTitle = note.noteTitle
        }
        insertTodos(todos = todos)
    }

    suspend fun deleteNoteWithTodos(noteWithToDos: NoteWithToDos) {
        deleteNote(note = noteWithToDos.note)
        noteWithToDos.todos?.let { deleteTodos(todos = it) }
    }

    @Query("SELECT * FROM table_notes")
    fun getNotesWithTodos(): LiveData<List<NoteWithToDos>>

    @Delete(entity = Note::class)
    suspend fun deleteNote(note: Note)

    @Delete(entity = ToDo::class)
    suspend fun deleteTodos(todos: List<ToDo>)

}
