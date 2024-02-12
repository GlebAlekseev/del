package com.glebalekseev.lab.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.glebalekseev.lab.R
import com.glebalekseev.lab.controller.NoteController
import com.glebalekseev.lab.entity.Note
import com.glebalekseev.lab.fragment.NoteDetailFragment
import com.glebalekseev.lab.fragment.NoteListFragment

class MainActivity : AppCompatActivity(), NoteListFragment.NoteListListener {
    companion object {
        private const val TAG = "MainActivity >> "
        private const val REQUEST_CODE_EDIT = 1
    }

    private var noteController: NoteController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        noteController = NoteController(this)
        Log.i(TAG, "onCreate")
    }

    override fun onNoteClick(note: Note) {
        val noteDetailFrameLayout = findViewById<FrameLayout>(R.id.noteDetailFl)
        if (noteDetailFrameLayout == null) {
            startActivityForResult(
                DetailActivity.getIntent(
                    context = this,
                    note = note,
                    isAdd = false
                ), REQUEST_CODE_EDIT
            )
        } else {
            val noteDetailFragment = NoteDetailFragment.getInstance(note)
            supportFragmentManager.beginTransaction()
                .replace(R.id.noteDetailFl, noteDetailFragment)
                .commit()
        }
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