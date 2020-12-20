package com.example.chuck_norris.categories.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chuck_norris.categories.ui.data.CategoriesViewEffect
import com.example.chuck_norris.categories.ui.data.CategoriesViewEvent
import com.example.chuck_norris.categories.ui.data.CategoriesViewState
import com.example.chuck_norris.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class CategoriesViewModel : ViewModel() {

    private val _currentViewState = MutableLiveData<CategoriesViewState>()
    val currentViewState: LiveData<CategoriesViewState>
        get() = _currentViewState

    private val _viewEffect = MutableLiveData<CategoriesViewEffect>()
    val viewEffect: LiveData<CategoriesViewEffect>
        get() = _viewEffect

    init {
        _currentViewState.value = CategoriesViewState()
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
        _currentViewState.value = _currentViewState.value!!.copy(isLoading = true)

        viewModelScope.launch(Dispatchers.IO) {
            delay(5000L)

            withContext(Dispatchers.Main) {
                _currentViewState.value = CategoriesViewState(
                    false,
                    listOf(
                        Category("One"),
                        Category("Two"),
                        Category("Three"),
                        Category("Four"),
                        Category("Five"),
                        Category("Six"),
                        Category("Seven"),
                    )
                )
            }
        }
    }

}

inline val <T> T.exhaustive
    get() = this