package com.example.chuck_norris.categories.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chuck_norris.categories.data.mappers.toUI
import com.example.chuck_norris.categories.data.usecase.GetCategoriesUseCase
import com.example.chuck_norris.categories.domain.Category
import com.example.chuck_norris.categories.ui.data.CategoriesViewEffect
import com.example.chuck_norris.categories.ui.data.CategoriesViewEvent
import com.example.chuck_norris.categories.ui.data.CategoriesViewState
import com.example.chuck_norris.extensions.exhaustive
import com.example.chuck_norris.network.abstractions.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class CategoriesViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<CategoriesViewState>()
    val viewState: LiveData<CategoriesViewState>
        get() = _viewState

    private val _viewEffect = MutableLiveData<CategoriesViewEffect>()
    val viewEffect: LiveData<CategoriesViewEffect>
        get() = _viewEffect

    init {
        _viewState.value = CategoriesViewState()
    }

    /**
     * Process the events coming from the view
     */
    fun processEvent(event: CategoriesViewEvent) {
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
            val result = getCategoriesUseCase.execute(GetCategoriesUseCase.Params())

            resultToViewState(result)
        }
    }

    /**
     * Transform the result into a valuable ViewState
     */
    private suspend fun resultToViewState(result: Either<List<Category>, Exception>) {
        when (result) {
            is Either.Value -> {
                withContext(Dispatchers.Main) {
                    _viewState.value = _viewState.value!!.copy(isLoading = false, categories = result.packet.toUI())
                }

            }
            is Either.Error -> {
                withContext(Dispatchers.Main) {
                    _viewEffect.value = CategoriesViewEffect.ShowError(result.packet.message!!)
                }
            }
        }.exhaustive
    }

}
