package com.example.chuck_norris.jokes.ui.detail.data

sealed class JokeDetailViewEffect {
    data class OpenJokeOnBrowser(var jokeUrl: String) : JokeDetailViewEffect()
    object UpdateFavoriteIcon : JokeDetailViewEffect()
}