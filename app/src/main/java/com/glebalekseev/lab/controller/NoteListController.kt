package com.glebalekseev.lab.controller

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.glebalekseev.lab.App
import com.glebalekseev.lab.fragment.NoteListFragment
import com.glebalekseev.lab.rv.NoteAdapter

class NoteListController(
    private val noteRV: RecyclerView,
    private val noteAdapter: NoteAdapter,
    private val listener: NoteListFragment.NoteListListener
) {
    private val repository = (noteRV.context.applicationContext as App).noteRepository

    fun initRV() {
        noteAdapter.onItemClick = { note ->
            listener.onNoteClick(note)
        }
        noteAdapter.onItemChangeDone = { note ->
            repository.saveNote(note)
        }
        noteRV.adapter = noteAdapter
        noteRV.layoutManager = LinearLayoutManager(noteRV.context)

        repository.notes.addObserver { notes ->
            noteAdapter.list = notes
            noteAdapter.notifyDataSetChanged()
        }
    }
}