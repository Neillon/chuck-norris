package com.example.chuck_norris.categories.presentation.categories.data

sealed class CategoriesViewEffect {
    data class ShowError(var message: String): CategoriesViewEffect()
}
