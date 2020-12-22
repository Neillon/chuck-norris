package com.example.chuck_norris.jokes.ui.search.data

sealed class SearchJokesViewEvent{
    data class SearchJokeByDescriptionEvent(var description: String): SearchJokesViewEvent()
}