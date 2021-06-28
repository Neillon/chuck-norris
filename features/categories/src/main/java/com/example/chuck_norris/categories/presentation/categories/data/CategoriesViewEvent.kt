package com.example.chuck_norris.categories.presentation.categories.data

sealed class CategoriesViewEvent {
    object LoadCategories: CategoriesViewEvent()
    object RefreshCategories: CategoriesViewEvent()
}
