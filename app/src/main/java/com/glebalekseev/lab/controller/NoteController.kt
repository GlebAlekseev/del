package com.glebalekseev.lab.controller

import androidx.core.math.MathUtils
import com.glebalekseev.lab.App
import com.glebalekseev.lab.entity.Note
import com.glebalekseev.lab.listener.NoteListener
import com.glebalekseev.lab.view.NoteView

class NoteController(
    private val noteView: NoteView,
    private val startActivityForResult: (Note?, Boolean) -> Unit,
    var currentIndex: Int = 0
) : NoteListener {
    private val repository = (noteView.context.applicationContext as App).noteRepository

    init {
        loadNoteView()
    }

    fun saveNote(note: Note){
        repository.saveNote(note)
        loadNoteView()
    }

    override fun onAdd() {
        startActivityForResult.invoke(null,true)
    }

    override fun onEdit() {
        val note = repository.getNote(currentIndex)
        if (note == null) onAdd()
        else {
            startActivityForResult.invoke(note, false)
        }
    }

    override fun onShowLast() {
        val count = repository.getNoteCount()
        currentIndex = MathUtils.clamp(count - 1, 0, Int.MAX_VALUE)
        loadNoteView()
    }

    override fun onShowNext() {
        val count = repository.getNoteCount()
        currentIndex = MathUtils.clamp(currentIndex + 1, 0, count - 1)
        loadNoteView()
    }

    override fun onShowPrev() {
        val count = repository.getNoteCount()
        currentIndex = MathUtils.clamp(currentIndex - 1, 0, count - 1)
        loadNoteView()
    }

    private fun setupNote(note: Note?) {
        note?.let {
            noteView.title = it.title
            noteView.description = it.description
        }
    }

    private fun loadNoteView() {
        val note = repository.getNote(currentIndex)
        setupNote(note)
    }
}