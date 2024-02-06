package com.glebalekseev.lab.listener

interface NoteListener {
    fun onAdd()
    fun onEdit()
    fun onShowLast()
    fun onShowNext()
    fun onShowPrev()
}