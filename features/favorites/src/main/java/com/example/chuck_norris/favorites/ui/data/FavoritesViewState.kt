package com.example.chuck_norris.favorites.ui.data

import com.example.chuck_norris.ui.JokeUI

data class FavoritesViewState(
    var isLoading: Boolean = false,
    var jokes: List<JokeUI> = emptyList(),
    var error: String? = null
)