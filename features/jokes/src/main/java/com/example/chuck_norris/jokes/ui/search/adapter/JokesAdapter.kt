package com.example.chuck_norris.jokes.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chuck_norris.ui.JokeUI
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.chuck_norris.jokes.R
import com.example.chuck_norris.jokes.databinding.ItemJokeBinding

class JokesAdapter(
    val favoriteJokeItemClick: JokesItemClick
) : ListAdapter<JokeUI, JokesAdapter.FavoriteViewHolder>(JokesDiffCallback) {

    private lateinit var binding: ItemJokeBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        binding = ItemJokeBinding.inflate(LayoutInflater.from(parent.context))
        return FavoriteViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size

    /**
     * Insert data into recycler view Adapter
     */
    fun insertData(data: List<JokeUI>) {
        submitList(data)
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