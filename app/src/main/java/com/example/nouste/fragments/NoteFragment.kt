package com.example.nouste.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.nouste.R
import com.example.nouste.adapters.NoteFragmentAdapter
import com.example.nouste.data.tables.Note
import com.example.nouste.data.tables.ToDo
import com.example.nouste.databinding.FragmentNoteBinding
import com.example.nouste.viewmodels.NoteViewModel
import com.google.android.material.tabs.TabLayout

class NoteFragment : Fragment() {

    companion object {
        const val IMAGE_REQUEST_CODE = 1
    }

    private lateinit var binding: FragmentNoteBinding

    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(this).get(NoteViewModel::class.java)
    }

    private val args: NoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(layoutInflater, R.layout.fragment_note, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupActionBar(view)
        setupViewPager()
        setupTabs()
        setupListeners()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> requireView().findNavController().popBackStack()
        }
        return true
    }

    private fun setupListeners() {
        binding.btnSaveNote.setOnClickListener {
            getData()
        }

        binding.btnBack.setOnClickListener {
            requireView().findNavController().popBackStack()
        }
    }

    private fun getData() {
        val fragments = requireActivity().supportFragmentManager.fragments
        var noteData: Note? = null
        var todosData: List<ToDo>? = null
        for (fragment in fragments) {
            if (fragment is NoteEditFragment) {
                noteData = fragment.getData()
            } else if (fragment is TodoListFragment) {
                todosData = fragment.getItems()
            }
        }
        noteViewModel.saveNote(noteData, todosData)
        requireView().findNavController().navigate(R.id.returnToHome)

    }

    private fun setupViewPager() {
        val viewPagerAdapter =
            NoteFragmentAdapter(
                requireActivity().supportFragmentManager,
                lifecycle,
                noteId = args.noteId
            )
        binding.viewPager.apply {
            adapter = viewPagerAdapter
            isUserInputEnabled = false
        }
    }

    private fun setupTabs() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) binding.viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    private fun setupActionBar(view: View) {
        (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.noteToolBar))
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
        }
    }
}