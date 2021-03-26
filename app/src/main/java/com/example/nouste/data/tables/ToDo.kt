package com.example.nouste.data.tables

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "table_todo",
    foreignKeys = [ForeignKey(
        entity = Note::class,
        parentColumns = ["id"],
        childColumns = ["noteId"],
        onDelete = CASCADE
    )]
)
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val taskName: String,
    val noteId: Int
)