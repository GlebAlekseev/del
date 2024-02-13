package com.glebalekseev.lab.data

import com.glebalekseev.lab.App
import com.glebalekseev.lab.core.MyObservable
import com.glebalekseev.lab.data.room.NoteDao
import com.glebalekseev.lab.entity.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteRepository(
    private val noteDao: NoteDao
) {
    val notes: MyObservable<List<Note>> = MyObservable(listOf())

    init {
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.getAllNotes().collect {
                withContext(Dispatchers.Main) {
                    notes.setNewValue(it)
                }
            }
        }
    }

    @Synchronized
    fun getNoteCount() = notes.value.size

    @Synchronized
    fun getNote(index: Int): Note? = notes.value.getOrNull(index)

    @Synchronized
    fun addNote(title: String, description: String, isDone: Boolean) {
        noteDao.addNote(
            Note(
                title = title,
                description = description,
                isDone = isDone
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
            noteDao.updateNote(note)
        }
    }
}