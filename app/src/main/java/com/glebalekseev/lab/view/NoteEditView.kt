package com.glebalekseev.lab.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import com.glebalekseev.lab.R
import com.glebalekseev.lab.listener.NoteDetailListener

class NoteEditView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private var titleEditTextId: Int = -1
    private var descriptionEditTextId: Int = -1
    private var saveViewId: Int = -1

    private val titleEditText: EditText by lazy { findViewById(titleEditTextId) }
    private val descriptionEditText: EditText by lazy { findViewById(descriptionEditTextId) }
    private val saveView: View by lazy { findViewById(saveViewId) }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.NoteEditView)
        val titleEditTextId =
            typedArray.getResourceId(R.styleable.NoteEditView_titleEditTextId, View.NO_ID)
        val descriptionEditTextId =
            typedArray.getResourceId(R.styleable.NoteEditView_descriptionEditTextId, View.NO_ID)
        val saveViewId = typedArray.getResourceId(R.styleable.NoteEditView_saveViewId, View.NO_ID)

        typedArray.recycle()

        if (
            titleEditTextId == View.NO_ID || descriptionEditTextId == View.NO_ID
            || saveViewId == View.NO_ID
        ) {
            throw IllegalArgumentException("All required attributes must be set in NoteEditView")
        }

        this.titleEditTextId = titleEditTextId
        this.descriptionEditTextId = descriptionEditTextId
        this.saveViewId = saveViewId
    }

    fun setNoteEditListener(noteDetailListener: NoteDetailListener) {
        saveView.setOnClickListener { noteDetailListener.onSave() }
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