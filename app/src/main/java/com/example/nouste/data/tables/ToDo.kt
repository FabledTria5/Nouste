package com.example.nouste.data.tables

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "table_todo")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var taskName: String,
    var done: Boolean,
    var noteTitle: String
)