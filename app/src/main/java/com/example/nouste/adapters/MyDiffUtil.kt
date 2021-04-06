package com.example.nouste.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.nouste.data.relations.NoteWithToDos

class MyDiffUtil(
    private val oldList: List<NoteWithToDos>,
    private val newList: List<NoteWithToDos>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.count()

    override fun getNewListSize() = newList.count()

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].note.id == newList[newItemPosition].note.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].note.id != newList[newItemPosition].note.id -> false
            else -> true
        }
    }
}