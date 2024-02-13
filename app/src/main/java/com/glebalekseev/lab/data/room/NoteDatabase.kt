package com.glebalekseev.lab.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.glebalekseev.lab.entity.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
