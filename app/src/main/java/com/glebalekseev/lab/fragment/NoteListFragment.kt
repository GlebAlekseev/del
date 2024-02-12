package com.glebalekseev.lab.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.glebalekseev.lab.R
import com.glebalekseev.lab.controller.NoteListController
import com.glebalekseev.lab.entity.Note

class NoteListFragment : Fragment() {
    interface NoteListListener {
        fun onNoteClick(note: Note)
    }

    private var noteListController: NoteListController? = null

    private val listener: NoteListListener by lazy {
        (activity as? NoteListListener)
            ?: throw RuntimeException("To use NoteListFragment the activity must implement NoteListListListener")
    }
    private lateinit var noteListView: ListView
    private val noteAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            mutableListOf<String>()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteListView = view.findViewById(R.id.noteListView)
        noteListController = NoteListController(noteListView, noteAdapter, listener)
        noteListController?.initListView()
    }
}