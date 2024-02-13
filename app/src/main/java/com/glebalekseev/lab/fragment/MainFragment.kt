package com.glebalekseev.lab.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.glebalekseev.lab.R
import com.glebalekseev.lab.controller.NoteController
import com.glebalekseev.lab.entity.Note


class MainFragment : Fragment(), NoteListFragment.NoteListListener  {
    private var noteController: NoteController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteController = NoteController(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteController?.setupFab()
    }

    override fun onNoteClick(note: Note) {
        noteController?.onNoteClick(note)
    }
}