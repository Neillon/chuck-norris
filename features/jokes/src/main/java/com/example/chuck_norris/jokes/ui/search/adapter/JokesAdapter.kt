package com.example.chuck_norris.jokes.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.chuck_norris.jokes.R
import com.example.chuck_norris.jokes.databinding.ItemJokeBinding
import com.example.chuck_norris.ui.JokeUI

class JokesDiffCallback : DiffUtil.ItemCallback<JokeUI>() {
    override fun areItemsTheSame(oldItem: JokeUI, newItem: JokeUI) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: JokeUI, newItem: JokeUI) =
        oldItem.id == newItem.id
}

class JokesAdapter(
    val favoriteJokeItemClick: JokesItemClick
) : ListAdapter<JokeUI, JokesAdapter.FavoriteViewHolder>(JokesDiffCallback()) {

    private lateinit var binding: ItemJokeBinding
    private val jokes: MutableList<JokeUI> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        binding = ItemJokeBinding.inflate(LayoutInflater.from(parent.context))
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