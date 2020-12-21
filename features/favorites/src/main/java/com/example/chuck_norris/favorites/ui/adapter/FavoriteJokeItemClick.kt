package com.example.chuck_norris.favorites.ui.adapter

import com.example.chuck_norris.ui.JokeUI

interface FavoriteJokeItemClick {
    fun onItemClick(joke: JokeUI)
}