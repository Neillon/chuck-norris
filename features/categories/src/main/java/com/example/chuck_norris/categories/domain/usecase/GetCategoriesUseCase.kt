package com.example.chuck_norris.categories.domain.usecase

import com.example.chuck_norris.abstractions.UseCase
import com.example.chuck_norris.categories.data.repository.CategoriesRepository
import com.example.chuck_norris.categories.domain.Category
import com.example.chuck_norris.network.abstractions.Either

class GetCategoriesUseCase(
    private val repository: CategoriesRepository
): UseCase<Either<List<Category>, Exception>, GetCategoriesUseCase.Params> {
    class Params()

    override suspend fun execute(params: Params): Either<List<Category>, Exception> {
        return repository.getCategories()
    }
}