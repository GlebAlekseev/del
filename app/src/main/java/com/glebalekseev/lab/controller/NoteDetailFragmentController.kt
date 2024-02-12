package com.glebalekseev.lab.controller

import com.glebalekseev.lab.App
import com.glebalekseev.lab.entity.Note
import com.glebalekseev.lab.listener.NoteDetailListener
import com.glebalekseev.lab.view.NoteEditView

class NoteDetailFragmentController(
    private val noteEditView: NoteEditView,
    private val note: Note,
) : NoteDetailListener {
    private val repository = (noteEditView.context.applicationContext as App).noteRepository

    override fun onSave() {
        repository.saveNote(
            Note(
                id = note.id,
                title = noteEditView.title,
                description = noteEditView.description,
                isDone = noteEditView.isDone
            )
        )
    }

    fun setupNote(note: Note) {
        noteEditView.title = note.title
        noteEditView.description = note.description
        noteEditView.isDone = note.isDone
    }
}