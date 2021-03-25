package com.example.nouste.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    fun getDate(): String = runBlocking {
        val dateFormatter = SimpleDateFormat("EEEE", Locale.getDefault())
        val monthFormatter = SimpleDateFormat("LLLL", Locale.getDefault())

        "${dateFormatter.format(Date())} ${
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        }, ${monthFormatter.format(Date())}"
    }
}

