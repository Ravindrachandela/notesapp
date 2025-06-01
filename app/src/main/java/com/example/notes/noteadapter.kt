package com.example.notes

import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.ActivityRecItemBinding

class noteadapter(private val notes: List<NoteItem>) : RecyclerView.Adapter<noteadapter.NoteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
      val  binding = ActivityRecItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteViewHolder(binding)
    }
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       val note=notes[position]
        holder.bind(note)
    }
    override fun getItemCount(): Int {
       return notes.size
    }
    class NoteViewHolder (private val binding:ActivityRecItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(note: NoteItem) {
            fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
             binding.etitle.text = note.title.toEditable()
             binding.edisc.text=note.discription.toEditable()
        }

    }
}
