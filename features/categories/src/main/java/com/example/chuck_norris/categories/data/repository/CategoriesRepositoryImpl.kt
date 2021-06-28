package com.example.chuck_norris.categories.data.repository

import com.example.chuck_norris.categories.data.api.CategoriesApi
import com.example.chuck_norris.categories.data.mappers.toDomain
import com.example.chuck_norris.categories.data.mappers.toEntity
import com.example.chuck_norris.categories.domain.abstractions.CategoriesRepository
import com.example.chuck_norris.common.Either
import com.example.chuck_norris.database.ChuckNorrisDatabase
import com.example.chuck_norris.entities.Category
import com.example.chuck_norris.network.exception.BaseNetworkException
import com.example.chuck_norris.network.exception.GenericNetworkException
import com.example.chuck_norris.network.manager.NetworkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CategoriesRepositoryImpl(
    private val database: ChuckNorrisDatabase,
    private val api: CategoriesApi
) : CategoriesRepository {
    override suspend fun getCategories(): Flow<Either<List<Category>, Exception>> = flow {
        try {
            val localData = database.categoriesDao().getAll().map { it.toDomain() }
            emit(Either.value(localData))

            val remoteData =
                NetworkManager.doAsyncRequest { api.getCategories() }.map { it.toEntity() }
            database.categoriesDao().insertAll(*remoteData.toTypedArray())

            emit(Either.value(remoteData.map { it.toDomain() }))

        } catch (e: Exception) {
            Either.error(e)
        } catch (e: BaseNetworkException) {
            Either.error(GenericNetworkException(e.message ?: ""))
        }
    }.flowOn(Dispatchers.IO)
}