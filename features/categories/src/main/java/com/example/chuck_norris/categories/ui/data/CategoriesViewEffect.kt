package com.example.chuck_norris.categories.ui.data

sealed class CategoriesViewEffect {
    data class ShowError(var message: String): CategoriesViewEffect()
}
