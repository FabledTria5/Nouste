package com.example.nouste.data.tables

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "table_notes", indices = [Index(value = ["noteTitle"], unique = true)])
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val noteTitle: String,
    val noteText: String,
    val noteImage: String?,
    val noteGradient: Int
)