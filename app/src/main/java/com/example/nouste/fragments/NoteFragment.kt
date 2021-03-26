package com.example.nouste.fragments

import android.app.Activity.RESULT_OK
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
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.nouste.R
import com.example.nouste.databinding.FragmentNoteBinding
import com.example.nouste.viewmodels.NoteViewModel

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
        setupListeners()
        if (args.noteId > 0) loadNote()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == IMAGE_REQUEST_CODE && data != null) {
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
            startActivityForResult(photoPickIntent, IMAGE_REQUEST_CODE)
        }

        binding.etNoteTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.textInputLayout2.error = "Note must have title!"
                    binding.isInputValid = false
                } else {
                    binding.isInputValid = true
                    binding.textInputLayout2.error = null
                }
            }
        })
    }

    private fun setupActionBar(view: View) {
        (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.noteToolBar))
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(false)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
    }

    private fun loadNote() {

    }
}