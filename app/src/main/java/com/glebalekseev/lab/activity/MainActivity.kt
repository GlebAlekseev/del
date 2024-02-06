package com.glebalekseev.lab.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.glebalekseev.lab.entity.Note
import com.glebalekseev.lab.R
import com.glebalekseev.lab.controller.NoteController
import com.glebalekseev.lab.view.NoteView

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity >> "
        private const val KEY_CURRENT_NOTE_INDEX = "current_note_index"
        private const val REQUEST_CODE_EDIT = 1
    }

    private var noteController: NoteController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentNoteIndex = savedInstanceState?.getInt(
            KEY_CURRENT_NOTE_INDEX
        ) ?: 0
        val noteView = findViewById<NoteView>(R.id.noteView)
        noteController = NoteController(
            noteView = noteView,
            currentIndex = currentNoteIndex,
            startActivityForResult = { note, isAdd ->
                startActivityForResult(
                    EditActivity.getIntent(
                        context = this,
                        note = note,
                        isAdd = isAdd
                    ), REQUEST_CODE_EDIT
                )
            }
        ).also {
            noteView.setNoteListener(it)
        }
        Log.i(TAG, "onCreate")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_EDIT){
            if (resultCode == RESULT_OK){
                val note = data?.getParcelableExtra<Note>(EditActivity.KEY_NOTE)
                note?.let {
                    noteController?.saveNote(it)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val currentNoteIndex = noteController?.currentIndex
        currentNoteIndex?.let {
            outState.putInt(KEY_CURRENT_NOTE_INDEX, it)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }
}