package com.example.chuck_norris.favorites.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.chuck_norris.ui.JokeUI

object FavoriteJokesDiffCallback : DiffUtil.ItemCallback<JokeUI>() {
    override fun areItemsTheSame(oldItem: JokeUI, newItem: JokeUI) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: JokeUI, newItem: JokeUI) =
        oldItem.id == newItem.id
}