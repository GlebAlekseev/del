package com.glebalekseev.lab.controller

import android.content.Context
import com.glebalekseev.lab.App
import com.glebalekseev.lab.entity.Note

class NoteController(context: Context) {
    private val repository = (context.applicationContext as App).noteRepository

    fun saveNote(note: Note) {
        repository.saveNote(note)
    }
}