package com.glebalekseev.lab.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.glebalekseev.lab.R
import com.glebalekseev.lab.entity.Note

class NoteAdapter() : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    var onItemClick: ((Note) -> Unit)? = null
    var onItemChangeDone: ((Note) -> Unit)? = null
    var list: List<Note> = emptyList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val isDoneCB: CheckBox = itemView.findViewById(R.id.isDoneCB)
        val titleTV: TextView = itemView.findViewById(R.id.titleTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list.getOrNull(position)
        item?.let {
            holder.titleTV.text = it.title
            holder.isDoneCB.isChecked = it.isDone
            holder.titleTV.setOnClickListener {
                val note = list.getOrNull(position)
                note?.let {
                    onItemClick?.invoke(it)
                }
            }
            holder.isDoneCB.setOnClickListener {
                val note = list.getOrNull(position)
                note?.let {
                    onItemChangeDone?.invoke(it.copy(isDone = holder.isDoneCB.isChecked))
                }
            }
        }
    }
}