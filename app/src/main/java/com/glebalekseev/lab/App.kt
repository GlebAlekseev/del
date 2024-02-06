package com.glebalekseev.lab

import android.app.Application
import com.glebalekseev.lab.data.NoteRepository

class App : Application() {
    val noteRepository = NoteRepository()
}