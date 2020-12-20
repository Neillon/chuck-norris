package com.example.chuck_norris.categories.data.usecase

import com.example.chuck_norris.abstractions.UseCase
import com.example.chuck_norris.categories.data.repository.CategoriesRepository
import com.example.chuck_norris.categories.domain.Category
import com.example.chuck_norris.network.abstractions.Either
import com.example.chuck_norris.network.error_handling.NetworkError

class GetCategoriesUseCase(
    private val repository: CategoriesRepository
): UseCase<Either<List<Category>, NetworkError>, GetCategoriesUseCase.Params> {
    class Params()

    override suspend fun execute(params: Params): Either<List<Category>, NetworkError> {
        return repository.getCategories()
    }
}