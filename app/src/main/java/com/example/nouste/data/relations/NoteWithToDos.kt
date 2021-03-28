package com.example.nouste.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.nouste.data.tables.Note
import com.example.nouste.data.tables.ToDo

data class NoteWithToDos(
    @Embedded val note: Note?,
    @Relation(parentColumn = "noteTitle", entity = ToDo::class, entityColumn = "noteTitle")
    val todos: List<ToDo>?
)
