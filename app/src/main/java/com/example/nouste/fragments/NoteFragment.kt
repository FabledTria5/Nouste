package com.example.nouste.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.nouste.R
import com.example.nouste.databinding.FragmentNoteBinding
import com.example.nouste.viewmodels.NoteViewModel

class NoteFragment : Fragment() {

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
        if (args.noteId > 0) loadNote()
    }

    @SuppressLint("RestrictedApi")
    private fun setupActionBar(view: View) {
        (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.noteToolBar))
        (activity as AppCompatActivity).supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
    }

    private fun loadNote() {

    }
}