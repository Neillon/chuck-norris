package com.example.chuck_norris.categories.ui.data

import com.example.chuck_norris.ui.CategoryUI

data class CategoriesViewState(
    val isLoading: Boolean = false,
    var categories: List<CategoryUI>? = emptyList()
)