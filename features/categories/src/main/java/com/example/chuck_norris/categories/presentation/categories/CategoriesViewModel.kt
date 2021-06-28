package com.example.chuck_norris.categories.presentation.categories

import androidx.lifecycle.viewModelScope
import com.example.chuck_norris.categories.domain.usecase.GetCategoriesUseCaseImpl
import com.example.chuck_norris.categories.presentation.categories.data.CategoriesViewEffect
import com.example.chuck_norris.categories.presentation.categories.data.CategoriesViewEvent
import com.example.chuck_norris.categories.presentation.categories.data.CategoriesViewState
import com.example.chuck_norris.common.Either
import com.example.chuck_norris.common.StateViewModel
import com.example.chuck_norris.entities.Category
import com.example.chuck_norris.extensions.exhaustive
import com.example.chuck_norris.mappers.toUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class CategoriesViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCaseImpl
) : StateViewModel<CategoriesViewState, CategoriesViewEvent, CategoriesViewEffect>() {

    init {
        _viewState.value = CategoriesViewState()
    }

    /**
     * Process the events coming from the view
     */
    override fun processEvent(event: CategoriesViewEvent) {
        when (event) {
            CategoriesViewEvent.LoadCategories -> {
                loadCategories()
                Timber.i("LoadCategories event")
            }
            CategoriesViewEvent.RefreshCategories -> {
                loadCategories()
                Timber.i("RefreshCategories event")
            }
        }.exhaustive
    }

    /**
     * Load the categories from the network
     */
    private fun loadCategories() {
        _viewState.value = _viewState.value!!.copy(isLoading = true)

        viewModelScope.launch(Dispatchers.IO) {
            val result = getCategoriesUseCase.execute(Unit)
            result
                .collect { either ->
                    withContext(Dispatchers.Main) {
                        updateViewState(either)
                    }
                }
        }
    }

    /**
     * Transform the result into a valuable ViewState
     */
    private fun updateViewState(result: Either<List<Category>, Exception>) {
        when (result) {
            is Either.Value -> {
                _viewState.value = _viewState.value!!.copy(
                    isLoading = false,
                    categories = result.packet.map { it.toUI() }
                )
            }
            is Either.Error -> {
                _viewEffect.value = CategoriesViewEffect.ShowError(result.packet.message!!)
            }
        }.exhaustive
    }

}
