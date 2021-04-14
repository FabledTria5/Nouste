package com.example.nouste.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nouste.R
import com.example.nouste.adapters.HomeListAdapter
import com.example.nouste.adapters.listeners.MenuEventListener
import com.example.nouste.data.relations.NoteWithToDos
import com.example.nouste.databinding.FragmentHomeBinding
import com.example.nouste.enums.MenuEvents
import com.example.nouste.utils.GridSpacingItemDecorator
import com.example.nouste.utils.toDp
import com.example.nouste.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeListAdapter: HomeListAdapter

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(layoutInflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        clearOldFragments()
        setDate()
        setupListeners()
        observeNotes()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        homeListAdapter = HomeListAdapter(object : MenuEventListener {
            override fun onEvent(menuEvent: MenuEvents, noteWithToDos: NoteWithToDos) {
                when (menuEvent) {
                    MenuEvents.Change -> Toast.makeText(context, "Change", Toast.LENGTH_SHORT)
                        .show()
                    MenuEvents.Delete -> homeViewModel.deleteNote(noteWithToDos)
                }
            }
        })
        binding.rvNotesList.apply {
            adapter = homeListAdapter
            layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            itemAnimator = DefaultItemAnimator()
        }
        binding.rvNotesList.addItemDecoration(
            GridSpacingItemDecorator(
                spanCount = 2,
                spacing = 20.toDp(resources.displayMetrics.density),
                includeEdge = true
            )
        )
    }

    private fun observeNotes() {
        homeViewModel.notes.observe(viewLifecycleOwner) {
            homeListAdapter.apply {
                setData(it)
                notifyItemRangeChanged(0, itemCount)
            }
        }
    }

    private fun setupListeners() {
        binding.btnAddNote.setOnClickListener {
            HomeFragmentDirections.openNote(0).also {
                requireView().findNavController().navigate(it)
            }
        }
    }

    private fun clearOldFragments() {
        requireActivity().supportFragmentManager.apply {
            for (fragment in fragments) {
                if (fragment is NoteEditFragment || fragment is TodoListFragment) {
                    beginTransaction().remove(fragment).commit()
                }
            }
        }
    }

    private fun setDate() {
        binding.date = homeViewModel.getDate()
    }
}