package com.example.androidfinal.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidfinal.databinding.TagRecyclerLayoutBinding

class TagAdapter : ListAdapter<String, TagAdapter.TagViewHolder>(PostsDiffUtil()) {

    private var onItemClick: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        this.onItemClick = listener
    }

    inner class TagViewHolder(
        private val binding: TagRecyclerLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var tag: String

        fun bind() {
            tag = currentList[adapterPosition]

            with(binding) {
                titleId.text = tag
                titleId.setOnClickListener {
                    onItemClick?.invoke(tag)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TagViewHolder(
        TagRecyclerLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind()
    }

    class PostsDiffUtil : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}