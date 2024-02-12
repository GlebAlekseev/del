package com.glebalekseev.lab.controller

import android.content.Intent
import android.os.Bundle
import com.glebalekseev.lab.App
import com.glebalekseev.lab.R
import com.glebalekseev.lab.activity.DetailActivity
import com.glebalekseev.lab.entity.Note
import com.glebalekseev.lab.listener.NoteDetailListener
import com.glebalekseev.lab.view.NoteEditView

class NoteDetailActivityController(
    private val noteEditView: NoteEditView,
    private val note: Note?,
    private val setResultAndFinish: (Intent) -> Unit
) : NoteDetailListener {
    private val repository = (noteEditView.context.applicationContext as App).noteRepository

    override fun onSave() {
        val bundle = Bundle().apply {
            val note = if (note?.id == null) Note(
                title = noteEditView.title,
                description = noteEditView.description,
                isDone = noteEditView.isDone
            ) else Note(
                id = note.id,
                title = noteEditView.title,
                description = noteEditView.description,
                isDone = noteEditView.isDone
            )
            this.putParcelable(
                DetailActivity.KEY_NOTE,
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
            noteEditView.isDone = false
        } else {
            noteEditView.title = note.title
            noteEditView.description = note.description
            noteEditView.isDone = note.isDone
        }
    }
}