package com.glebalekseev.lab.controller

import android.widget.ArrayAdapter
import android.widget.ListView
import com.glebalekseev.lab.App
import com.glebalekseev.lab.fragment.NoteListFragment

class NoteListController(
    private val noteListView: ListView,
    private val noteAdapter: ArrayAdapter<String>,
    private val listener: NoteListFragment.NoteListListener
) {
    private val repository = (noteListView.context.applicationContext as App).noteRepository

    fun initListView() {
        noteListView.adapter = noteAdapter
        noteListView.setOnItemClickListener { parent, view, position, id ->
            val selectedNote = repository.notes.value.getOrNull(position)
            selectedNote?.let {
                listener.onNoteClick(it)
            }
        }
        repository.notes.addObserver { notes ->
            val noteTitles = notes.map { it.title }
            noteAdapter.clear()
            noteAdapter.addAll(noteTitles)
            noteAdapter.notifyDataSetChanged()
        }
    }
}