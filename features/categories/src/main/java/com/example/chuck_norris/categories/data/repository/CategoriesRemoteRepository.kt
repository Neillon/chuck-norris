package com.example.chuck_norris.categories.data.repository

import com.example.chuck_norris.categories.data.api.CategoriesApi
import com.example.chuck_norris.categories.data.mappers.toCategoryList
import com.example.chuck_norris.categories.domain.Category
import com.example.chuck_norris.network.abstractions.Either
import com.example.chuck_norris.network.exception.BaseNetworkException
import com.example.chuck_norris.network.exception.GenericNetworkException
import com.example.chuck_norris.network.manager.NetworkManager

class CategoriesRemoteRepository(
    private val categoriesApi: CategoriesApi,
    private val networkManager: NetworkManager
) : CategoriesRepository {

    override suspend fun getCategories(): Either<List<Category>, BaseNetworkException> = try {
        val data = networkManager.doAsyncRequest {
            categoriesApi.getCategories()
        }.toCategoryList()

        Either.value(data)
    } catch (exception: BaseNetworkException) {
        Either.error(exception)
    } catch (exception: Exception) {
        Either.error(GenericNetworkException(exception.message ?: ""))
    }

}