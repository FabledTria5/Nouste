package com.example.nouste.data.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "table_notes", indices = [Index(value = ["noteTitle"], unique = true)])
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val noteTitle: String,
    val noteText: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val noteImage: ByteArray?,
    val noteGradient: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Note

        if (id != other.id) return false
        if (noteTitle != other.noteTitle) return false
        if (noteText != other.noteText) return false
        if (!noteImage.contentEquals(other.noteImage)) return false
        if (noteGradient != other.noteGradient) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + noteTitle.hashCode()
        result = 31 * result + noteText.hashCode()
        result = 31 * result + noteImage.contentHashCode()
        result = 31 * result + noteGradient
        return result
    }
}