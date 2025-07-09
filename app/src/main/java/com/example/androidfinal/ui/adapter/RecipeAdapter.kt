package com.example.androidfinal.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidfinal.R
import com.example.androidfinal.model.Recipe

class RecipeAdapter(
    private var items: List<Recipe>,
    private val onItemClick: ((Recipe) -> Unit)? = null
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageItem)
        val title: TextView = itemView.findViewById(R.id.itemTitle)
        val difficulty: TextView = itemView.findViewById(R.id.difficulty)
        val rating: TextView = itemView.findViewById(R.id.rating)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(items[adapterPosition])
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<Recipe>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe_card, parent, false)
        return RecipeViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.name
        holder.difficulty.text = "Difficulty: ${item.difficulty}"
        holder.rating.text = "★ ${item.rating}"

        Glide.with(holder.itemView.context).load(item.image).into(holder.image)
    }

    override fun getItemCount(): Int = items.size

}