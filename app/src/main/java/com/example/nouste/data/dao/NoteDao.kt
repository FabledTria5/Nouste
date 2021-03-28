package com.example.nouste.data.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
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

    @Query("SELECT * FROM table_notes")
    fun getNotesWithTodos(): LiveData<List<NoteWithToDos>>

}
