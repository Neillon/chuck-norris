package com.example.chuck_norris.categories.data.mappers

import com.example.chuck_norris.categories.domain.Category
import com.example.chuck_norris.categories.ui.data.CategoryUI

internal fun List<String>.toCategoryList() = this.map { Category(it) }

internal fun String.toCategory() = Category(this)

internal fun Category.toUI() = CategoryUI(this.name)
internal fun CategoryUI.toDomain() = Category(this.name)