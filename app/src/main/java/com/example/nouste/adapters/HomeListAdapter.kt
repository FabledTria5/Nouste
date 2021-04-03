package com.example.nouste.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nouste.R
import com.example.nouste.data.relations.NoteWithToDos
import com.example.nouste.utils.Gradients
import com.example.nouste.utils.getSubList
import com.example.nouste.utils.setGradient
import com.google.android.material.card.MaterialCardView

class HomeListAdapter : RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder>() {

    private val notesList = arrayListOf<NoteWithToDos>()

    inner class HomeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val noteTitle = itemView.findViewById<TextView>(R.id.tvNoteTitle)
        private val todosList = itemView.findViewById<RecyclerView>(R.id.rvTodoItems)

        fun bind(fullNote: NoteWithToDos) {

            noteTitle.text = fullNote.note.noteTitle
            if (!fullNote.todos.isNullOrEmpty()) {
                todosList.apply {
                    adapter = NotesWithTodosAdapter().also {
                        it.addItems(fullNote.todos.getSubList(4))
                        it.notifyItemRangeChanged(0, fullNote.todos.count())
                    }
                    layoutManager = LinearLayoutManager(itemView.context)
                }
            }

            (itemView as MaterialCardView).getChildAt(0)
                .setGradient(gradient = Gradients.values()[fullNote.note.noteGradient])

            itemView.setOnLongClickListener {
                val animZoom = AnimationUtils.loadAnimation(it.context, R.anim.zoom_in)
                it.startAnimation(animZoom)
                return@setOnLongClickListener true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeListViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
    )

    override fun onBindViewHolder(holder: HomeListViewHolder, position: Int) {
        holder.bind(notesList[position])
    }

    override fun getItemCount() = notesList.count()

    fun addItems(notes: List<NoteWithToDos>) = notesList.addAll(notes)

    fun clearItems() = notesList.clear()

}