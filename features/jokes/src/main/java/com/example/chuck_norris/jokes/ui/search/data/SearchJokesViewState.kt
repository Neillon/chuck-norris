package com.example.chuck_norris.jokes.ui.search.data

import com.example.chuck_norris.ui.JokeUI

data class SearchJokesViewState(
    val isLoading: Boolean = false,
    val searchText: String = "",
    val jokes: List<JokeUI> = emptyList(),
    val error: String? = null,
)