package com.example.chuck_norris.categories.mock

import com.example.chuck_norris.categories.domain.abstractions.CategoriesRepository
import Category
import com.example.chuck_norris.network.abstractions.Either
import com.example.chuck_norris.network.exception.BadRequestException
import com.example.chuck_norris.network.exception.BaseNetworkException
import com.example.chuck_norris.network.exception.GenericNetworkException

object CategoriesRepositoryFake {

    class WithValue: CategoriesRepository {
        override suspend fun getCategories(): Either<List<Category>, BaseNetworkException> = Either.value(Mocks.Categories.ALL)
    }

    class WithBadRequestError: CategoriesRepository {
        override suspend fun getCategories(): Either<List<Category>, BaseNetworkException> = Either.Error(BadRequestException())
    }

    class WithError: CategoriesRepository {
        override suspend fun getCategories(): Either<List<Category>, BaseNetworkException> = Either.error(GenericNetworkException("Generic Error"))
    }

}