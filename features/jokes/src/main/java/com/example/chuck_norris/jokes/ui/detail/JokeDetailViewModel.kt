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
import kotlinx.coroutines.withContext
import timber.log.Timber

class JokeDetailViewModel :
    StateViewModel<JokeDetailViewState, JokeDetailViewEvent, JokeDetailViewEffect>() {

    init {
        _viewState.value = JokeDetailViewState(isLoadingJoke = false, joke = null)
    }

    override fun processEvent(event: JokeDetailViewEvent) {
        when (event) {
            is JokeDetailViewEvent.LoadJokeFromInternet -> {
                loadJokeFromInternet(event.category)
            }
            is JokeDetailViewEvent.LoadJokeFromArguments -> {
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
        _viewState.value = _viewState.value!!.copy(
            isLoadingJoke = true
        )

        viewModelScope.launch(Dispatchers.IO) {
            delay(2000L)
            withContext(Dispatchers.Main) {
                _viewState.value = _viewState.value!!.copy(
                    isLoadingJoke = false,
                    joke = JokeUI(
                        id = "9ovbd5b1t66_x92jwrq1yq",
                        value = "Chuck Norris once rode a bull, and nine months later it had a calf.",
                        iconUrl = "iconUrl",
                        url = "https://api.chucknorris.io/jokes/9ovbd5b1t66_x92jwrq1yq"
                    )
                )
            }
        }
    }

    /**
     * Favorite a joke and send it to a local database
     */
    private fun favoriteJoke(joke: JokeUI) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000L)

            withContext(Dispatchers.Main) {
                _viewEffect.value = JokeDetailViewEffect.UpdateFavoriteIcon
            }
        }
    }
}