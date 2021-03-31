package com.example.nouste.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nouste.R
import com.example.nouste.data.tables.ToDo

class NotesWithTodosAdapter :
    RecyclerView.Adapter<NotesWithTodosAdapter.NotesWithTodosViewHolder>() {

    private val todosList = arrayListOf<ToDo>()

    inner class NotesWithTodosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
        private val todoName: TextView = itemView.findViewById(R.id.etTodoTitle)

        fun bind(position: Int) {
            checkBox.apply {
                isChecked = todosList[position].done
                isEnabled = false
                alpha = (1 - position.toFloat() / 10)
            }
            todoName.apply {
                text = todosList[position].taskName
                alpha = (1 - (position.toFloat() / 10))
                if (todosList[position].done) paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NotesWithTodosViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.todo_list_text_item, parent, false)
        )

    override fun onBindViewHolder(holder: NotesWithTodosViewHolder, position: Int) =
        holder.bind(position = position)

    override fun getItemCount() = todosList.count()

    fun addItems(items: List<ToDo>) = todosList.addAll(items)
}