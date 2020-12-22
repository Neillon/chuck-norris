package com.example.chuck_norris.jokes.ui.search

import androidx.lifecycle.viewModelScope
import com.example.chuck_norris.abstractions.StateViewModel
import com.example.chuck_norris.domain.entities.Joke
import com.example.chuck_norris.extensions.exhaustive
import com.example.chuck_norris.jokes.data.mappers.toUI
import com.example.chuck_norris.jokes.domain.usecase.SearchJokeByDescriptionUseCase
import com.example.chuck_norris.jokes.ui.search.data.SearchJokesViewEvent
import com.example.chuck_norris.jokes.ui.search.data.SearchJokesViewState
import com.example.chuck_norris.network.abstractions.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchJokesViewModel(
    private val searchJokeByDescriptionUseCase: SearchJokeByDescriptionUseCase
) : StateViewModel<SearchJokesViewState, SearchJokesViewEvent, Nothing>() {

    init {
        _viewState.value = SearchJokesViewState()
    }

    /**
     * Process View Events
     */
    override fun processEvent(event: SearchJokesViewEvent) {
        when (event) {
            is SearchJokesViewEvent.SearchJokeByDescriptionEvent -> searchJokes(event.description)
        }.exhaustive
    }

    /**
     * Search all jokes by description
     */
    private fun searchJokes(description: String) {
        _viewState.value = _viewState.value!!.copy(isLoading = true, searchText = description)

        viewModelScope.launch(Dispatchers.IO) {
            var result = searchJokeByDescriptionUseCase.execute(
                SearchJokeByDescriptionUseCase.Params(description)
            )

            resultToViewState(result)
        }
    }

    /**
     * Transform result to ViewState
     */
    private suspend fun resultToViewState(result: Either<List<Joke>, String>) {
        when (result) {
            is Either.Value -> updateViewStateWithValue(result.packet)
            is Either.Error -> updateViewStateWithError(result.packet)
        }.exhaustive
    }

    /**
     * Update current ViewState with new jokes
     */
    private suspend fun updateViewStateWithValue(packet: List<Joke>) =
        withContext(Dispatchers.Main) {
            _viewState.value = _viewState.value!!.copy(isLoading = false, error = null, jokes = packet.toUI(false))
        }

    /**
     * Update current ViewState with error
     */
    private suspend fun updateViewStateWithError(packet: String) =
        withContext(Dispatchers.Main) {
            _viewState.value = _viewState.value!!.copy(isLoading = false, error = packet)
        }
}