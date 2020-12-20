package com.example.chuck_norris.categories.ui.data

import com.example.chuck_norris.model.Category

data class CategoriesViewState(
    val isLoading: Boolean = false,
    val categories: List<Category>? = emptyList(),
    val error: String? = null
)