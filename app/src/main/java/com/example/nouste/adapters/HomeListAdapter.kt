package com.example.nouste.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nouste.R
import com.example.nouste.data.relations.NoteWithToDos

class HomeListAdapter : RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder>() {

    private val notesList = arrayListOf<NoteWithToDos>()

    inner class HomeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val noteTitle = itemView.findViewById<TextView>(R.id.tvNoteTitle)
        private val todosList = itemView.findViewById<RecyclerView>(R.id.rvTodoItems)

        fun bind(position: Int) {
            noteTitle.text = notesList[position].note?.noteTitle
            
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeListViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
    )

    override fun onBindViewHolder(holder: HomeListViewHolder, position: Int) {
        holder.bind(position = position)
    }

    override fun getItemCount() = notesList.count()

    fun addItems(notes: List<NoteWithToDos>) = notesList.addAll(notes)

}