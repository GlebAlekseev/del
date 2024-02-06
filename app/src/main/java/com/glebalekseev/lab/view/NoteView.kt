package com.glebalekseev.lab.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import com.glebalekseev.lab.NoteListener
import com.glebalekseev.lab.R

class NoteView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private var titleEditTextId: Int = -1
    private var descriptionEditTextId: Int = -1
    private var addViewId: Int = -1
    private var saveViewId: Int = -1
    private var showPrevViewId: Int = -1
    private var showNextViewId: Int = -1
    private var showLastViewId: Int = -1

    private val titleEditText: EditText by lazy { findViewById(titleEditTextId) }
    private val descriptionEditText: EditText by lazy { findViewById(descriptionEditTextId) }
    private val addView: View by lazy { findViewById(addViewId) }
    private val saveView: View by lazy { findViewById(saveViewId) }
    private val showPrevView: View by lazy { findViewById(showPrevViewId) }
    private val showNextView: View by lazy { findViewById(showNextViewId) }
    private val showLastView: View by lazy { findViewById(showLastViewId) }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.NoteView)
        val titleEditTextId =
            typedArray.getResourceId(R.styleable.NoteView_titleEditTextId, View.NO_ID)
        val descriptionEditTextId =
            typedArray.getResourceId(R.styleable.NoteView_descriptionEditTextId, View.NO_ID)
        val addViewId = typedArray.getResourceId(R.styleable.NoteView_addViewId, View.NO_ID)
        val saveViewId = typedArray.getResourceId(R.styleable.NoteView_saveViewId, View.NO_ID)
        val showPrevViewId =
            typedArray.getResourceId(R.styleable.NoteView_showPrevViewId, View.NO_ID)
        val showNextViewId =
            typedArray.getResourceId(R.styleable.NoteView_showNextViewId, View.NO_ID)
        val showLastViewId =
            typedArray.getResourceId(R.styleable.NoteView_showLastViewId, View.NO_ID)
        typedArray.recycle()

        if (
            titleEditTextId == View.NO_ID || descriptionEditTextId == View.NO_ID
            || addViewId == View.NO_ID || saveViewId == View.NO_ID
            || showPrevViewId == View.NO_ID || showNextViewId == View.NO_ID
            || showLastViewId == View.NO_ID
        ) {
            throw IllegalArgumentException("All required attributes must be set in NoteView")
        }

        this.titleEditTextId = titleEditTextId
        this.descriptionEditTextId = descriptionEditTextId
        this.addViewId = addViewId
        this.saveViewId = saveViewId
        this.showPrevViewId = showPrevViewId
        this.showNextViewId = showNextViewId
        this.showLastViewId = showLastViewId
    }

    fun setNoteListener(noteListener: NoteListener) {
        addView.setOnClickListener { noteListener.onAdd() }
        saveView.setOnClickListener { noteListener.onSave() }
        showPrevView.setOnClickListener { noteListener.onShowPrev() }
        showNextView.setOnClickListener { noteListener.onShowNext() }
        showLastView.setOnClickListener { noteListener.onShowLast() }
    }

    var title: String
        get() = titleEditText.text.toString()
        set(value) {
            titleEditText.setText(value)
        }

    var description: String
        get() = descriptionEditText.text.toString()
        set(value) {
            descriptionEditText.setText(value)
        }
}