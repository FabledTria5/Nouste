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

    @Update
    suspend fun updateNote(note: Note)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateTodos(todosData: List<ToDo>)

    @Query(value = "SELECT * FROM table_notes")
    fun getNotesWithTodos(): LiveData<List<NoteWithToDos>>

    @Query(value = "SELECT * FROM table_notes WHERE id = :noteId")
    suspend fun getNote(noteId: Int): Note

    @Query(value = "SELECT * FROM table_todo WHERE noteTitle = (SELECT noteTitle FROM table_notes WHERE id = :noteId)")
    suspend fun getTodos(noteId: Int): List<ToDo>

    @Delete(entity = Note::class)
    suspend fun deleteNote(note: Note)

    @Delete(entity = ToDo::class)
    suspend fun deleteTodos(todos: List<ToDo>)

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

}
