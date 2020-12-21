package com.example.chuck_norris.jokes.ui.detail.data

import com.example.chuck_norris.ui.CategoryUI
import com.example.chuck_norris.ui.JokeUI

sealed class JokeDetailViewEvent {
    data class LoadJokeFromInteernetView(var category: CategoryUI): JokeDetailViewEvent()
    data class LoadJokeView(var joke: JokeUI): JokeDetailViewEvent()
    data class FavoriteJoke(var joke: JokeUI): JokeDetailViewEvent()
    data class OpenJokeOnBrowser(var jokeUrl: String) : JokeDetailViewEvent()
}