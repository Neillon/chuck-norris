package com.example.chuck_norris.categories.domain.abstractions

import com.example.chuck_norris.common.Either
import com.example.chuck_norris.entities.Category
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun getCategories(): Flow<Either<List<Category>, Exception>>
}