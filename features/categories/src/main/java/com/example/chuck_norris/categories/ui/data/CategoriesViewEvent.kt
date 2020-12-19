package com.example.chuck_norris.categories.ui.data

sealed class CategoriesViewEvent {
    object LoadCategories: CategoriesViewEvent()
    object RefreshCategories: CategoriesViewEvent()
}
