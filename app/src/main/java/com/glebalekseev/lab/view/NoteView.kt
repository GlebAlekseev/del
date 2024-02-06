package com.glebalekseev.lab.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.glebalekseev.lab.listener.NoteListener
import com.glebalekseev.lab.R

class NoteView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private var titleTextViewId: Int = -1
    private var descriptionTextViewId: Int = -1
    private var addViewId: Int = -1
    private var editViewId: Int = -1
    private var showPrevViewId: Int = -1
    private var showNextViewId: Int = -1
    private var showLastViewId: Int = -1

    private val titleTextView: TextView by lazy { findViewById(titleTextViewId) }
    private val descriptionTextView: TextView by lazy { findViewById(descriptionTextViewId) }
    private val addView: View by lazy { findViewById(addViewId) }
    private val editView: View by lazy { findViewById(editViewId) }
    private val showPrevView: View by lazy { findViewById(showPrevViewId) }
    private val showNextView: View by lazy { findViewById(showNextViewId) }
    private val showLastView: View by lazy { findViewById(showLastViewId) }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.NoteView)
        val titleTextViewId =
            typedArray.getResourceId(R.styleable.NoteView_titleTextViewId, View.NO_ID)
        val descriptionTextViewId =
            typedArray.getResourceId(R.styleable.NoteView_descriptionTextViewId, View.NO_ID)
        val addViewId = typedArray.getResourceId(R.styleable.NoteView_addViewId, View.NO_ID)
        val editViewId = typedArray.getResourceId(R.styleable.NoteView_editViewId, View.NO_ID)
        val showPrevViewId =
            typedArray.getResourceId(R.styleable.NoteView_showPrevViewId, View.NO_ID)
        val showNextViewId =
            typedArray.getResourceId(R.styleable.NoteView_showNextViewId, View.NO_ID)
        val showLastViewId =
            typedArray.getResourceId(R.styleable.NoteView_showLastViewId, View.NO_ID)
        typedArray.recycle()

        if (
            titleTextViewId == View.NO_ID || descriptionTextViewId == View.NO_ID
            || addViewId == View.NO_ID || editViewId == View.NO_ID
            || showPrevViewId == View.NO_ID || showNextViewId == View.NO_ID
            || showLastViewId == View.NO_ID
        ) {
            throw IllegalArgumentException("All required attributes must be set in NoteView")
        }

        this.titleTextViewId = titleTextViewId
        this.descriptionTextViewId = descriptionTextViewId
        this.addViewId = addViewId
        this.editViewId = editViewId
        this.showPrevViewId = showPrevViewId
        this.showNextViewId = showNextViewId
        this.showLastViewId = showLastViewId
    }

    fun setNoteListener(noteListener: NoteListener) {
        addView.setOnClickListener { noteListener.onAdd() }
        editView.setOnClickListener { noteListener.onEdit() }
        showPrevView.setOnClickListener { noteListener.onShowPrev() }
        showNextView.setOnClickListener { noteListener.onShowNext() }
        showLastView.setOnClickListener { noteListener.onShowLast() }
    }

    var title: String
        get() = titleTextView.text.toString()
        set(value) {
            titleTextView.text = value
        }

    var description: String
        get() = descriptionTextView.text.toString()
        set(value) {
            descriptionTextView.text = value
        }
}