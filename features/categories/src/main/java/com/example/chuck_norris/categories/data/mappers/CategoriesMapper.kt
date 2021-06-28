package com.example.chuck_norris.categories.data.mappers

import com.example.chuck_norris.database.entities.CategoryEntity
import com.example.chuck_norris.entities.Category

internal fun CategoryEntity.toDomain() = Category(this.name)
internal fun String.toDomain() = Category(this)

internal fun String.toEntity() = CategoryEntity(name = this)