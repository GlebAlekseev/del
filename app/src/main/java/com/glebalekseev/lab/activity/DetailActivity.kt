package com.glebalekseev.lab.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.glebalekseev.lab.R
import com.glebalekseev.lab.controller.NoteDetailActivityController
import com.glebalekseev.lab.entity.Note
import com.glebalekseev.lab.view.NoteEditView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val note = intent.getParcelableExtra<Note>(KEY_NOTE)
        val noteEditView = findViewById<NoteEditView>(R.id.noteEditView)
        NoteDetailActivityController(
            noteEditView = noteEditView,
            note = note,
            setResultAndFinish = { intent ->
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        ).also {
            noteEditView.setNoteEditListener(it)
            if (savedInstanceState == null) {
                it.setupNote(note)
            }
        }
    }

    companion object {
        const val KEY_NOTE = "note"
        fun getIntent(context: Context, note: Note?): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                note?.let {
                    val bundle = Bundle().apply {
                        putParcelable(KEY_NOTE, note)
                    }
                    this.putExtras(bundle)
                }
            }
        }
    }
}