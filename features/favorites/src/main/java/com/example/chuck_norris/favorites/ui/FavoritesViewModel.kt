package com.example.chuck_norris.favorites.ui

import androidx.lifecycle.viewModelScope
import com.example.chuck_norris.common.StateViewModel
import com.example.chuck_norris.extensions.exhaustive
import com.example.chuck_norris.favorites.data.mappers.toUI
import com.example.chuck_norris.favorites.domain.usecase.GetFavoriteJokesUseCase
import com.example.chuck_norris.favorites.ui.data.FavoritesViewEvent
import com.example.chuck_norris.favorites.ui.data.FavoritesViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesViewModel(
    private val getFavoriteJokesUseCase: GetFavoriteJokesUseCase
) : StateViewModel<FavoritesViewState, FavoritesViewEvent, Nothing>() {

    init {
        _viewState.value = FavoritesViewState()
    }

    override fun processEvent(event: FavoritesViewEvent) {
        when (event) {
            FavoritesViewEvent.LoadFavoriteJokes -> loadJokesFromDatabase()
        }.exhaustive
    }

    /**
     * Load favorite jokes from database
     */
    private fun loadJokesFromDatabase() {
        _viewState.value = _viewState.value!!.copy(isLoading = true)

        viewModelScope.launch(Dispatchers.IO) {
            val result = getFavoriteJokesUseCase.execute(GetFavoriteJokesUseCase.Params())

            withContext(Dispatchers.Main) {
                when (result) {
                    is com.example.chuck_norris.common.Either.Value ->
                        _viewState.value = _viewState.value!!.copy(
                            isLoading = false,
                            jokes = result.packet.toUI()
                        )
                    is com.example.chuck_norris.common.Either.Error ->
                        _viewState.value =
                            _viewState.value!!.copy(
                                isLoading = false,
                                error = result.packet.message
                            )
                }.exhaustive
            }
        }
    }

}