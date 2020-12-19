package com.example.chuck_norris.categories.ui

import androidx.lifecycle.ViewModel
import com.example.chuck_norris.categories.ui.data.CategoriesViewEvent
import com.example.chuck_norris.categories.ui.data.CategoriesViewState
import timber.log.Timber

class CategoriesViewModel: ViewModel() {

    private val currentViewState = CategoriesViewState()

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

    }

}

val Any.exhaustive
    get() = this