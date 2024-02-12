package com.glebalekseev.lab.data

import com.glebalekseev.lab.core.MyObservable
import com.glebalekseev.lab.entity.Note

class NoteRepository {
    val notes = MyObservable(
        listOf<Note>(
            Note(
                title = "Запись 1",
                description = "Описание для заметки 1",
                isDone = false
            ),
            Note(
                title = "Запись 2",
                description = "Описание для заметки 2",
                isDone = false
            ),
            Note(
                title = "Запись 3",
                description = "Описание для заметки 3",
                isDone = true
            ),
            Note(
                title = "Запись 4",
                description = "Описание для заметки 4",
                isDone = false
            ),
        )
    )


    @Synchronized
    fun getNoteCount() = notes.value.size

    @Synchronized
    fun getNote(index: Int): Note? = notes.value.getOrNull(index)

    @Synchronized
    fun addNote(title: String, description: String, isDone: Boolean) {
        notes.setNewValue(
            notes.value + listOf(
                Note(
                    title = title,
                    description = description,
                    isDone = isDone
                )
            )
        )
    }

    @Synchronized
    fun saveNote(note: Note) {
        val localNotes = notes.value
        val oldNote = localNotes.find { it.id == note.id }
        if (oldNote == null) {
            addNote(title = note.title, description = note.description, isDone = note.isDone)
        } else {
            val index = localNotes.indexOf(oldNote)
            val result = localNotes.toMutableList()
            result[index] = note
            notes.setNewValue(result)
        }
    }
}