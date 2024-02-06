package com.glebalekseev.lab.controller

import android.content.Intent
import android.os.Bundle
import com.glebalekseev.lab.App
import com.glebalekseev.lab.R
import com.glebalekseev.lab.activity.EditActivity
import com.glebalekseev.lab.entity.Note
import com.glebalekseev.lab.listener.NoteEditListener
import com.glebalekseev.lab.view.NoteEditView

class NoteEditController(
    private val noteEditView: NoteEditView,
    private val isAdd: Boolean,
    private val noteId: String?,
    private val setResultAndFinish: (Intent) -> Unit
) : NoteEditListener {
    private val repository = (noteEditView.context.applicationContext as App).noteRepository

    override fun onSave() {
        val bundle = Bundle().apply {
            val note = if (noteId == null) Note(
                title = noteEditView.title,
                description = noteEditView.description
            ) else Note(
                id = noteId,
                title = noteEditView.title,
                description = noteEditView.description
            )
            this.putParcelable(
                EditActivity.KEY_NOTE,
                note
            )
        }
        val resultIntent = Intent().apply {
            putExtras(bundle)
        }
        setResultAndFinish.invoke(resultIntent)
    }

    fun setupNote(note: Note?) {
        if (note == null) {
            noteEditView.title = String.format(
                noteEditView.resources.getString(R.string.text_example_note_title),
                repository.getNoteCount() + 1
            )
            noteEditView.description =
                noteEditView.resources.getString(R.string.text_example_note_description)
        } else {
            noteEditView.title = note.title
            noteEditView.description = note.description
        }
    }
}