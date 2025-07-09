package com.example.androidfinal.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidfinal.databinding.RecipeLayoutBinding
import com.example.androidfinal.model.Recipe

class RecipeAdapter : ListAdapter<Recipe, RecipeAdapter.RecipeViewHolder>(RecipeDiffUtil()) {

    private var onItemClick: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        this.onItemClick = listener
    }

    inner class RecipeViewHolder(
        private val binding: RecipeLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var model: Recipe

        fun bind() {
            model = currentList[adapterPosition]

            with(binding) {
                Glide.with(root.context).load(model.image).into(imageItem)
                itemTitle.text = model.name
                difficulty.text = model.difficulty
                rating.text = model.rating.toString()
                root.setOnClickListener {
                    onItemClick?.invoke(model.id ?: 1)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecipeViewHolder(
        RecipeLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind()
    }

    class RecipeDiffUtil : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }
}