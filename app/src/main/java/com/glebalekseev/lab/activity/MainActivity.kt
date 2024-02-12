package com.glebalekseev.lab.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.glebalekseev.lab.R
import com.glebalekseev.lab.controller.NoteController
import com.glebalekseev.lab.entity.Note
import com.glebalekseev.lab.fragment.NoteListFragment

class MainActivity : AppCompatActivity(), NoteListFragment.NoteListListener {
    companion object {
        private const val TAG = "MainActivity >> "
        const val REQUEST_CODE_EDIT = 1
    }

    private var noteController: NoteController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        noteController = NoteController(this)
        noteController?.setupFab()
        Log.i(TAG, "onCreate")
    }

    override fun onNoteClick(note: Note) {
        noteController?.onNoteClick(note)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_EDIT) {
            if (resultCode == RESULT_OK) {
                val note = data?.getParcelableExtra<Note>(DetailActivity.KEY_NOTE)
                note?.let {
                    noteController?.saveNote(it)
                }
            }
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