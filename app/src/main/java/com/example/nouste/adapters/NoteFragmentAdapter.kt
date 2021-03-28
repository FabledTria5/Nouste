package com.example.nouste.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nouste.fragments.NoteEditFragment
import com.example.nouste.fragments.TodoListFragment

class NoteFragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, val noteId: Int) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NoteEditFragment.instance(noteId = noteId)
            1 -> TodoListFragment.instance(noteId = noteId)
            else -> NoteEditFragment.instance(noteId = noteId)
        }
    }
}