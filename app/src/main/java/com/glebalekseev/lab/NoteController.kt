package com.glebalekseev.lab

import android.app.Application
import androidx.core.math.MathUtils
import com.glebalekseev.lab.view.NoteView

class NoteController(private val noteView: NoteView, var currentIndex: Int = 0) : NoteListener {
    private val repository = (noteView.context.applicationContext as App).noteRepository

    init {
        loadNoteView()
    }

    override fun onAdd() {
        repository.addNote(title = noteView.title, description = noteView.description)
        loadNoteView()
    }

    override fun onSave() {
        val id = repository.getNote(currentIndex)?.id
        if (id == null) repository.addNote(
            title = noteView.title,
            description = noteView.description
        )
        else repository.saveNote(
            Note(
                id = id,
                title = noteView.title,
                description = noteView.description
            )
        )
        loadNoteView()
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