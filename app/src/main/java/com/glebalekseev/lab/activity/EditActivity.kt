package com.glebalekseev.lab.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.glebalekseev.lab.R
import com.glebalekseev.lab.controller.NoteEditController
import com.glebalekseev.lab.entity.Note
import com.glebalekseev.lab.view.NoteEditView

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val isAdd = intent.getBooleanExtra(KEY_IS_ADD, true)
        val note = intent.getParcelableExtra<Note>(KEY_NOTE)
        val noteId = note?.id
            ?: (if (isAdd) null else throw RuntimeException("the note has not been transferred to edit mode "))

        val noteEditView = findViewById<NoteEditView>(R.id.noteEditView)
        NoteEditController(
            noteEditView = noteEditView,
            isAdd = isAdd,
            noteId = noteId,
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
        private const val KEY_IS_ADD = "is_add"
        fun getIntent(context: Context, isAdd: Boolean, note: Note?): Intent {
            return Intent(context, EditActivity::class.java).apply {
                note?.let {
                    val bundle = Bundle().apply {
                        putParcelable(KEY_NOTE, note)
                    }
                    this.putExtras(bundle)
                    this.putExtra(KEY_IS_ADD, isAdd)
                }
            }
        }
    }
}