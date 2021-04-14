package com.example.nouste.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nouste.R
import com.example.nouste.adapters.TodoAdapter
import com.example.nouste.data.tables.ToDo
import com.example.nouste.databinding.FragmentTodoListBinding
import com.example.nouste.viewmodels.TodosListViewModel

class TodoListFragment : Fragment() {

    companion object {
        private const val CURRENT_NOTE = "currentNote"

        fun instance(noteId: Int): TodoListFragment {
            TodoListFragment().also {
                val args = Bundle()
                args.putInt(CURRENT_NOTE, noteId)
                it.arguments = args
                return it
            }
        }
    }

    private val todosListViewModel: TodosListViewModel by viewModels()

    private lateinit var binding: FragmentTodoListBinding
    private lateinit var todosAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(layoutInflater, R.layout.fragment_todo_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupListeners()
        if (requireArguments().getInt(CURRENT_NOTE) > 0) loadTodos()
    }

    private fun loadTodos() {
        todosListViewModel.getTodos(requireArguments().getInt(CURRENT_NOTE)).also {
            todosAdapter.addTodos(items = it)
            todosAdapter.notifyDataSetChanged()
        }
    }

    private fun setupListeners() {
        todosAdapter = TodoAdapter()
        binding.rvTodosItems.apply {
            adapter = todosAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.btnAddTodo.setOnClickListener {
            todosAdapter.addTodo(ToDo(0, "", false, ""))
            todosAdapter.notifyItemInserted(todosAdapter.itemCount)
        }
    }

    fun getItems(): List<ToDo> = todosAdapter.getTodos()
}