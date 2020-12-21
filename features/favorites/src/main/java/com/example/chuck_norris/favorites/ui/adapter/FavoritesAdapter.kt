package com.example.chuck_norris.favorites.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.chuck_norris.favorites.R
import com.example.chuck_norris.favorites.databinding.ItemFavoriteJokeBinding
import com.example.chuck_norris.ui.JokeUI

class FavoriteJokesDiffCallback : DiffUtil.ItemCallback<JokeUI>() {
    override fun areItemsTheSame(oldItem: JokeUI, newItem: JokeUI) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: JokeUI, newItem: JokeUI) =
        oldItem.id == newItem.id
}

class FavoritesAdapter(
    val favoriteJokeItemClick: FavoriteJokeItemClick
) : ListAdapter<JokeUI, FavoritesAdapter.FavoriteViewHolder>(FavoriteJokesDiffCallback()) {

    private lateinit var binding: ItemFavoriteJokeBinding
    private val jokes: MutableList<JokeUI> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        binding = ItemFavoriteJokeBinding.inflate(LayoutInflater.from(parent.context))
        return FavoriteViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(jokes[position])
    }

    override fun getItemCount(): Int = jokes.size

    /**
     * Insert data into recycler view Adapter
     */
    fun insertData(data: List<JokeUI>) {
        jokes.clear()
        jokes.addAll(data)
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(joke: JokeUI) {
            binding.textViewFavoriteJokeDescription.text = joke.value
            binding.imageViewFavoriteJokeItem.load(joke.iconUrl) {
                crossfade(true)
                placeholder(R.drawable.ic_image_outline)
                transformations(RoundedCornersTransformation(8f))
            }
            itemView.setOnClickListener { favoriteJokeItemClick.onItemClick(joke) }
        }
    }
}