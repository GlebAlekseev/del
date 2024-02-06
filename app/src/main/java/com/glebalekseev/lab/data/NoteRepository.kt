package com.glebalekseev.lab.data

import com.glebalekseev.lab.entity.Note

class NoteRepository {
    private val notes = mutableListOf<Note>()

    @Synchronized
    fun getNoteCount() = notes.size

    @Synchronized
    fun getNote(index: Int): Note? = notes.getOrNull(index)

    @Synchronized
    fun addNote(title: String, description: String) {
        notes.add(
            Note(
                title = title,
                description = description
            )
        )
    }

    @Synchronized
    fun saveNote(note: Note) {
        val oldNote = notes.find { it.id == note.id }
        if (oldNote == null) {
            addNote(title = note.title, description = note.description)
        } else {
            val index = notes.indexOf(oldNote)
            notes[index] = note
        }
    }
}