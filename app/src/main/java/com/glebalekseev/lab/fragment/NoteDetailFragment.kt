package com.glebalekseev.lab.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.glebalekseev.lab.R
import com.glebalekseev.lab.controller.NoteDetailFragmentController
import com.glebalekseev.lab.entity.Note
import com.glebalekseev.lab.view.NoteEditView


class NoteDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noteEditView = view.findViewById<NoteEditView>(R.id.noteEditView)
        val note = arguments?.getParcelable<Note>(KEY_NOTE)
            ?: throw RuntimeException("Note arg is null")
        NoteDetailFragmentController(
            noteEditView = noteEditView,
            noteId = note.id
        ).also {
            noteEditView.setNoteEditListener(it)
            if (savedInstanceState == null) {
                it.setupNote(note)
            }
        }
    }

    companion object {
        private const val KEY_NOTE = "note"
        fun getInstance(note: Note): NoteDetailFragment {
            return NoteDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_NOTE, note)
                }
            }
        }
    }
}