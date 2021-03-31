package com.example.nouste.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.nouste.R
import com.example.nouste.adapters.HomeListAdapter
import com.example.nouste.databinding.FragmentHomeBinding
import com.example.nouste.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeListAdapter: HomeListAdapter

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

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
        setDate()
        setupListeners()
        observeNotes()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        homeListAdapter = HomeListAdapter()
        binding.rvNotesList.apply {
            adapter = homeListAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        val dividerItemDecorator = DividerItemDecoration(context, RecyclerView.VERTICAL)
        dividerItemDecorator.setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider, null)!!)
        binding.rvNotesList.addItemDecoration(dividerItemDecorator)
    }

    private fun observeNotes() {
        homeViewModel.notes.observe(viewLifecycleOwner) {
            homeListAdapter.apply {
                addItems(it)
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

    private fun setDate() {
        binding.date = homeViewModel.getDate()
    }
}