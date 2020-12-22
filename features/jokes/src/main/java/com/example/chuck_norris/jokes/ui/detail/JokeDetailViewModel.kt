package com.example.chuck_norris.jokes.ui.detail

import androidx.lifecycle.viewModelScope
import com.example.chuck_norris.abstractions.StateViewModel
import com.example.chuck_norris.extensions.exhaustive
import com.example.chuck_norris.jokes.data.mappers.toDomain
import com.example.chuck_norris.jokes.data.mappers.toUI
import com.example.chuck_norris.jokes.domain.usecase.FavoriteJokeUseCase
import com.example.chuck_norris.jokes.domain.usecase.FindJokeByRemoteIdUseCase
import com.example.chuck_norris.jokes.domain.usecase.GetRandomJokeByCategoryUseCase
import com.example.chuck_norris.jokes.ui.detail.data.JokeDetailViewEffect
import com.example.chuck_norris.jokes.ui.detail.data.JokeDetailViewEvent
import com.example.chuck_norris.jokes.ui.detail.data.JokeDetailViewState
import com.example.chuck_norris.network.abstractions.Either
import com.example.chuck_norris.ui.CategoryUI
import com.example.chuck_norris.ui.JokeUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JokeDetailViewModel(
    private val getRandomJokeByCategoryUseCase: GetRandomJokeByCategoryUseCase,
    private val favoriteJokeUseCase: FavoriteJokeUseCase,
    private val findJokeByRemoteIdUseCase: FindJokeByRemoteIdUseCase
) :
    StateViewModel<JokeDetailViewState, JokeDetailViewEvent, JokeDetailViewEffect>() {

    init {
        _viewState.value = JokeDetailViewState()
    }

    override fun processEvent(event: JokeDetailViewEvent) {
        when (event) {
            is JokeDetailViewEvent.LoadJokeFromInternet ->
                loadJokeFromInternet(event.category)

            is JokeDetailViewEvent.LoadJokeFromArguments -> {
                updateViewStateFromArguments(event.joke)
            }

            is JokeDetailViewEvent.FavoriteJoke -> favoriteJoke(event.joke)

            is JokeDetailViewEvent.OpenJokeOnBrowser ->
                _viewEffect.value = JokeDetailViewEffect.OpenJokeOnBrowser(event.jokeUrl)

        }.exhaustive
    }

    /**
     * Update the viewstate when data come from arguments
     */
    private fun updateViewStateFromArguments(joke: JokeUI) {
        if (joke.isFavorite)
            _viewEffect.value = JokeDetailViewEffect.UpdateFavoriteIcon

        _viewState.value = _viewState.value!!.copy(
            isLoadingJoke = false,
            joke = joke
        )
    }

    /**
     * Loads a random joke given a category
     */
    private fun loadJokeFromInternet(category: CategoryUI) {
        _viewState.value = _viewState.value!!.copy(
            isLoadingJoke = true,
            error = null
        )

        viewModelScope.launch(Dispatchers.IO) {
            val result = getRandomJokeByCategoryUseCase.execute(
                GetRandomJokeByCategoryUseCase.Params(category.name)
            )

            when (result) {
                is Either.Value -> withContext(Dispatchers.Main) {
                    _viewState.value = _viewState.value!!.copy(
                        isLoadingJoke = false,
                        joke = result.packet.toUI(),
                        error = null
                    )
                }
                is Either.Error -> withContext(Dispatchers.Main) {
                    _viewState.value = _viewState.value!!.copy(
                        isLoadingJoke = false,
                        joke = null,
                        error = result.packet.message
                    )
                }
            }.exhaustive
        }
    }

    /**
     * Favorite a joke and send it to a local database
     */
    private fun favoriteJoke(joke: JokeUI) {
        _viewState.value = _viewState.value!!.copy(favoritingJoke = true)

        viewModelScope.launch(Dispatchers.IO) {
            val result = favoriteJokeUseCase.execute(FavoriteJokeUseCase.Params(joke.toDomain()))

            when (result) {
                is Either.Value -> {
                    withContext(Dispatchers.Main) {
                        _viewState.value = _viewState.value!!.copy(
                            isLoadingJoke = false,
                            favoritingJoke = false
                        )

                        _viewEffect.value = JokeDetailViewEffect.UpdateFavoriteIcon
                    }
                }
                is Either.Error -> {
                    _viewState.value = _viewState.value!!.copy(
                        isLoadingJoke = false,
                        favoritingJoke = false,
                        error = result.packet.message
                    )
                }
            }.exhaustive
        }
    }
}