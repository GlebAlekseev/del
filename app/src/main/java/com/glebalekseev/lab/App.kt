package com.glebalekseev.lab

import android.app.Application
import androidx.room.Room
import com.glebalekseev.lab.data.NoteRepository
import com.glebalekseev.lab.data.room.NoteDatabase

class App : Application() {
    lateinit var noteRepository: NoteRepository
    override fun onCreate() {
        super.onCreate()
        val database = Room.databaseBuilder(this, NoteDatabase::class.java, "note_database")
            .allowMainThreadQueries()
            .build()
        noteRepository = NoteRepository(database.noteDao())
    }
}