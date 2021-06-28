package com.example.chuck_norris.categories.domain.usecase

import com.example.chuck_norris.common.Either
import com.example.chuck_norris.entities.Category
import com.example.chuck_norris.categories.domain.abstractions.CategoriesRepository
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCaseImpl(
    private val repository: CategoriesRepository
) : GetCategoriesUseCase {

    override suspend fun execute(params: Unit): Flow<Either<List<Category>, Exception>> {
        return repository.getCategories()
    }
}