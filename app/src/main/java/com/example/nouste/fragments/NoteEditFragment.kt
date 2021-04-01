package com.example.nouste.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nouste.R
import com.example.nouste.adapters.GradientAdapter
import com.example.nouste.adapters.listeners.OnGradientClickListener
import com.example.nouste.data.tables.Note
import com.example.nouste.databinding.FragmentNoteEditBinding
import com.example.nouste.utils.*

class NoteEditFragment : Fragment() {

    companion object {

        private const val CURRENT_NOTE = "currentNote"

        fun instance(noteId: Int): NoteEditFragment {
            NoteEditFragment().also {
                val args = Bundle()
                args.putInt(CURRENT_NOTE, noteId)
                it.arguments = args
                return it
            }
        }
    }

    private lateinit var binding: FragmentNoteEditBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_note_edit, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupListeners()
        setupRecyclerView()
        if (requireArguments().getInt(CURRENT_NOTE) > 0) loadNote()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == NoteFragment.IMAGE_REQUEST_CODE && data != null) {
            binding.rivNoteImage.setImageBitmap(getCapturedImage(data.data))
        }
    }

    @Suppress("DEPRECATION")
    private fun getCapturedImage(data: Uri?): Bitmap {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && data != null) {
            ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(requireActivity().contentResolver, data)
            )
        } else MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, data)
    }

    private fun setupListeners() {
        binding.btnAddImage.setOnClickListener {
            val photoPickIntent = Intent(Intent.ACTION_GET_CONTENT)
            photoPickIntent.type = "image/"
            startActivityForResult(photoPickIntent, NoteFragment.IMAGE_REQUEST_CODE)
        }

        binding.etNoteTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.textInputLayout2.error = "Note must have title!"
                } else {
                    binding.textInputLayout2.error = null
                }
            }
        })

        binding.btnShowGradients.setOnClickListener {
            binding.rvGradientsList.apply {
                if (isVisible) hide() else show()
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvGradientsList.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter =
                GradientAdapter(object : OnGradientClickListener {
                    override fun onClick(gradient: Gradients) {
                        binding.ivCurrentGradient.setGradient(gradient = gradient)
                    }
                }).also {
                    it.addGradients(Gradients.values())
                    it.notifyItemRangeChanged(0, Gradients.values().size)
                }
        }
    }

    private fun loadNote() {

    }

    fun getData(): Note {
        return Note(
            0,
            noteTitle = binding.etNoteTitle.text.toString(),
            noteText = binding.etNoteText.text.toString(),
            noteGradient = R.drawable.main_gradient,
            noteImage = binding.rivNoteImage.drawable.convertToByteArray()
        )
    }
}