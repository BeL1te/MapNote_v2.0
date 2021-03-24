package com.be1te.big.notemap.screens.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.be1te.big.notemap.R
import com.be1te.big.notemap.db.room.Note
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private var mListNotes = emptyList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mListNotes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = mListNotes[position].title
        holder.date.text = mListNotes[position].date
        holder.content.text = mListNotes[position].content
    }

    fun setList(list: List<Note>) {
        mListNotes = list
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.item_title_text
        val date = view.item_date_text
        val content = view.item_content_text

        fun bind(note: Note, context: Context) {
            title.text = note.title
            date.text = note.date
            content.text = note.content
        }
    }
}