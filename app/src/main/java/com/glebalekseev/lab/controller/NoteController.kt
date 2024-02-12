package com.glebalekseev.lab.controller

import android.app.Activity
import android.widget.FrameLayout
import com.glebalekseev.lab.App
import com.glebalekseev.lab.R
import com.glebalekseev.lab.activity.DetailActivity
import com.glebalekseev.lab.activity.MainActivity
import com.glebalekseev.lab.entity.Note
import com.glebalekseev.lab.fragment.NoteDetailFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteController(private val mainActivity: MainActivity) {
    private val repository = (mainActivity.applicationContext as App).noteRepository

    fun saveNote(note: Note) {
        repository.saveNote(note)
    }

    fun setupFab() = with(mainActivity) {
        val addNoteFab = findViewById<FloatingActionButton>(R.id.addNoteFab)
        addNoteFab.setOnClickListener {
            startDetailActivity(note = null, isAdd = true)
        }
    }

    fun onNoteClick(note: Note) = with(mainActivity) {
        val noteDetailFrameLayout = findViewById<FrameLayout>(R.id.noteDetailFl)
        if (noteDetailFrameLayout == null) {
            startDetailActivity(note = note, isAdd = false)
        } else {
            val noteDetailFragment = NoteDetailFragment.getInstance(note)
            supportFragmentManager.beginTransaction()
                .replace(R.id.noteDetailFl, noteDetailFragment)
                .commit()
        }
    }

    private fun Activity.startDetailActivity(note: Note?, isAdd: Boolean) {
        startActivityForResult(
            DetailActivity.getIntent(
                context = this,
                note = note,
            ), MainActivity.REQUEST_CODE_EDIT
        )
    }
}