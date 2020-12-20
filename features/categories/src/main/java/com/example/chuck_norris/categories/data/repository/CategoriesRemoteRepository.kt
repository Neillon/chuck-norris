package com.example.chuck_norris.categories.data.repository

import com.example.chuck_norris.categories.data.api.CategoriesApi
import com.example.chuck_norris.categories.data.mappers.toCategoryList
import com.example.chuck_norris.categories.domain.Category
import com.example.chuck_norris.network.abstractions.Either
import com.example.chuck_norris.network.error_handling.NetworkError
import com.example.chuck_norris.network.error_handling.NetworkErrorFactory
import com.example.chuck_norris.network.exception.BaseNetworkException
import com.example.chuck_norris.network.exception.GenericNetworkException
import com.example.chuck_norris.network.manager.NetworkManager

class CategoriesRemoteRepository(
    private val categoriesApi: CategoriesApi,
    private val networkManagerImpl: NetworkManager
) : CategoriesRepository {

    override suspend fun getCategories(): Either<List<Category>, NetworkError> = try {
        val data = networkManagerImpl.doAsyncRequest {
            categoriesApi.getCategories()
        }.toCategoryList()

        Either.left(data)
    } catch (exception: BaseNetworkException) {
        Either.right(NetworkErrorFactory.create(exception))
    } catch (exception: Exception) {
        Either.right(NetworkErrorFactory.create(
            exception = GenericNetworkException(exception.message ?: "")
        ))
    }

}