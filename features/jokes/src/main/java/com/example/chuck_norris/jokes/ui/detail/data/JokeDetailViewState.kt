package com.example.chuck_norris.jokes.ui.detail.data

import com.example.chuck_norris.ui.JokeUI

data class JokeDetailViewState(
    val joke: JokeUI? = null,
    val isLoadingJoke: Boolean = false,
    val error: String? = null,
    val favoriteJoke: Boolean = false
)