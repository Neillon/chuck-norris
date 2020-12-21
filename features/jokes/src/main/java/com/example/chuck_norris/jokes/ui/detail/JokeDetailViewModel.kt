package com.example.chuck_norris.jokes.ui.detail

import androidx.lifecycle.viewModelScope
import com.example.chuck_norris.abstractions.StateViewModel
import com.example.chuck_norris.extensions.exhaustive
import com.example.chuck_norris.jokes.ui.detail.data.JokeDetailViewEffect
import com.example.chuck_norris.jokes.ui.detail.data.JokeDetailViewEvent
import com.example.chuck_norris.jokes.ui.detail.data.JokeDetailViewState
import com.example.chuck_norris.ui.CategoryUI
import com.example.chuck_norris.ui.JokeUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class JokeDetailViewModel :
    StateViewModel<JokeDetailViewState, JokeDetailViewEvent, JokeDetailViewEffect>() {

    override fun processEvent(event: JokeDetailViewEvent) {
        when (event) {
            is JokeDetailViewEvent.LoadJokeFromInteernetView -> {
                loadJokeFromInternet(event.category)
            }
            is JokeDetailViewEvent.LoadJokeView -> {
                _viewState.value = _viewState.value!!.copy(
                    isLoadingJoke = false,
                    joke = event.joke
                )
            }
            is JokeDetailViewEvent.FavoriteJoke -> {
                favoriteJoke(event.joke)
            }
            is JokeDetailViewEvent.OpenJokeOnBrowser -> _viewEffect.value =
                JokeDetailViewEffect.OpenJokeOnBrowser(event.jokeUrl)

        }.exhaustive
    }

    /**
     * Loads a random joke given a category
     */
    private fun loadJokeFromInternet(category: CategoryUI) {
        Timber.i("Loaded from internet")
    }

    /**
     * Favorite a joke and send it to a local database
     */
    private fun favoriteJoke(joke: JokeUI) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000L)
            _viewEffect.value = JokeDetailViewEffect.UpdateFavoriteIcon
        }
    }
}