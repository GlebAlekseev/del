package com.glebalekseev.lab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.glebalekseev.lab.view.NoteView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val noteView = findViewById<NoteView>(R.id.noteView)
        val noteController = NoteController(noteView)
        noteView.setNoteListener(noteController)
    }
}