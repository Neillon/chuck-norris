package com.example.chuck_norris.categories.ui.data

import com.example.chuck_norris.model.Category

sealed class CategoriesViewEffect {
    data class UpdateCategoriesList(val newCategories: List<Category>)
}
