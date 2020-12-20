package com.example.chuck_norris.categories.ui.data

data class CategoriesViewState(
    val isLoading: Boolean = false,
    val categories: List<Category>? = emptyList(),
    val error: String? = null
)