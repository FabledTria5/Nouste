package com.example.nouste.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nouste.R
import com.example.nouste.data.tables.ToDo

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private val todosList = arrayListOf<ToDo>()

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val checkBox = itemView.findViewById<CheckBox>(R.id.checkBox)
        private val todoTitle = itemView.findViewById<EditText>(R.id.etTodoTitle)

        fun bind(position: Int) {
            todoTitle.setText(todosList[position].taskName, TextView.BufferType.EDITABLE)
            if (todosList[position].done) todoTitle.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            addDoneListener(position)
            addTitleListener(position)
        }

        private fun addDoneListener(position: Int) {
            checkBox.apply {
                isChecked = todosList[position].done
                setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) todoTitle.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    else todoTitle.paintFlags = 0
                    todosList[position].done = isChecked
                }
            }
        }

        private fun addTitleListener(position: Int) {
            todoTitle.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) todosList[position].taskName = (v as EditText).text.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.todo_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) =
        holder.bind(position = position)

    override fun getItemCount() = todosList.count()

    fun addTodo(task: ToDo) = todosList.add(task)

    fun getTodos() = todosList

}