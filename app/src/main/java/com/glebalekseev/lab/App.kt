package com.glebalekseev.lab

import android.app.Application

class App : Application() {
    val noteRepository = NoteRepository()
}