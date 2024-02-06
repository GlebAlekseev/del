package com.glebalekseev.lab

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.glebalekseev.lab.view.NoteView

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity >> "
        private const val KEY_CURRENT_NOTE_INDEX = "current_note_index"
    }

    private var noteController: NoteController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentNoteIndex = savedInstanceState?.getInt(
            KEY_CURRENT_NOTE_INDEX
        ) ?: 0
        val noteView = findViewById<NoteView>(R.id.noteView)
        noteController = NoteController(noteView = noteView, currentIndex = currentNoteIndex).also {
            noteView.setNoteListener(it)
        }
        Log.i(TAG, "onCreate")
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
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