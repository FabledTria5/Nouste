package com.example.nouste.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nouste.R
import com.example.nouste.adapters.listeners.MenuEventListener
import com.example.nouste.data.relations.NoteWithToDos
import com.example.nouste.enums.Gradients
import com.example.nouste.enums.MenuEvents
import com.example.nouste.utils.*
import com.google.android.material.card.MaterialCardView

class HomeListAdapter(private val menuEventListener: MenuEventListener) :
    RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder>() {

    private var notesList = emptyList<NoteWithToDos>()

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
                it.setZoom(true)
                openOptions(fullNote)
                return@setOnLongClickListener true
            }
        }

        private fun openOptions(fullNote: NoteWithToDos) {
            PopupMenu(itemView.context, itemView.findViewById(R.id.menuAnchor)).also {
                it.menuInflater.inflate(R.menu.popup_menu, it.menu)
                it.setOnDismissListener { itemView.setZoom(false) }
                it.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.btnChange -> menuEventListener.onEvent(MenuEvents.Change, fullNote)
                        R.id.btnDelete -> menuEventListener.onEvent(MenuEvents.Delete, fullNote)
                    }
                    return@setOnMenuItemClickListener true
                }
                it.show()
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

    fun setData(notes: List<NoteWithToDos>) {
        val diffUtil = MyDiffUtil(notesList, notes)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        notesList = notes
        diffResults.dispatchUpdatesTo(this)
    }

}