package com.glebalekseev.lab.controller

import android.os.Bundle
import android.widget.FrameLayout
import androidx.navigation.fragment.findNavController
import com.glebalekseev.lab.R
import com.glebalekseev.lab.entity.Note
import com.glebalekseev.lab.fragment.MainFragment
import com.glebalekseev.lab.fragment.NoteDetailFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteController(private val mainFragment: MainFragment) {
    fun setupFab() = with(mainFragment.requireView()) {
        val addNoteFab = findViewById<FloatingActionButton>(R.id.addNoteFab)
        addNoteFab.setOnClickListener {
            navigateToNoteDetailFragment()
        }
    }

    fun onNoteClick(note: Note) = with(mainFragment.requireView()) {
        val noteDetailFrameLayout = findViewById<FrameLayout>(R.id.noteDetailFl)
        if (noteDetailFrameLayout == null) {
            navigateToNoteDetailFragment(note)
        } else {
            val noteDetailFragment = NoteDetailFragment.newInstance(note)
            mainFragment.parentFragmentManager.beginTransaction()
                .replace(R.id.noteDetailFl, noteDetailFragment)
                .commit()
        }
    }

    fun navigateToNoteDetailFragment(note: Note? = null) {
        val navController = mainFragment.findNavController()
        navController.navigate(R.id.action_mainFragment_to_noteDetailFragment, Bundle().apply {
            note?.let { putParcelable(NoteDetailFragment.KEY_NOTE, it) }
        })
    }
}