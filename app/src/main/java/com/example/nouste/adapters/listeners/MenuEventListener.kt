package com.example.nouste.adapters.listeners

import com.example.nouste.data.relations.NoteWithToDos
import com.example.nouste.enums.MenuEvents

interface MenuEventListener {
    fun onEvent(menuEvent: MenuEvents, noteWithToDos: NoteWithToDos)
}