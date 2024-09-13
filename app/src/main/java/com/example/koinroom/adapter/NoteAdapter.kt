package com.example.koinroom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.koinroom.databinding.ItemNoteBinding
import com.example.koinroom.db.NoteEntity

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteAdapterViewHolder>() {
    private lateinit var binding: ItemNoteBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapterViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        binding = ItemNoteBinding.inflate(inflater, parent, false)
        return NoteAdapterViewHolder()
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: NoteAdapterViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    inner class NoteAdapterViewHolder() : RecyclerView.ViewHolder(binding.root){

        fun bind(noteEntity: NoteEntity){
            binding.apply {
                tvTitle.text = noteEntity.noteTitle
                tvDesc.text = noteEntity.noteDesc
            }

        }

    }

                                //object expression
    private val differCallback = object : DiffUtil.ItemCallback<NoteEntity>(){
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.noteId == newItem.noteId
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

}