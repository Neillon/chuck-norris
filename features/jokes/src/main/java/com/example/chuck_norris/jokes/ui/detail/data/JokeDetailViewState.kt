package com.example.chuck_norris.jokes.ui.detail.data

import com.example.chuck_norris.ui.JokeUI

data class JokeDetailViewState(
    val joke: JokeUI?,
    val isLoadingJoke: Boolean = false
)