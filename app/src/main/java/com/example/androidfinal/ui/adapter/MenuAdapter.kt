package com.example.androidfinal.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidfinal.model.Recipe
import com.example.androidfinal.databinding.ItemRecipeCardBinding

class MenuAdapter : ListAdapter<Recipe, MenuAdapter.MenuViewHolder>(MenuDiffUtil()) {

    private var onItemClick: ((Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int) -> Unit) {
        this.onItemClick = listener
    }

    inner class MenuViewHolder(
        private val binding: ItemRecipeCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var model: Recipe

        @SuppressLint("SetTextI18n")
        fun bind() {
            model = currentList[adapterPosition]

            with(binding) {
                Glide.with(root.context).load(model.image).into(imageItem)
                itemTitle.text = model.name
                difficulty.text = "Difficulty: ${model.difficulty}"
                rating.text = model.rating.toString()
                root.setOnClickListener {
                    onItemClick?.invoke(model.id!!)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MenuViewHolder(
        ItemRecipeCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind()
    }

    class MenuDiffUtil : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }
}