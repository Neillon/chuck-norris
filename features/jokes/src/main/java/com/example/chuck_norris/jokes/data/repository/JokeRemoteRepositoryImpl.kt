package com.example.chuck_norris.jokes.data.repository

import com.example.chuck_norris.domain.entities.Joke
import com.example.chuck_norris.jokes.data.api.JokeApi
import com.example.chuck_norris.jokes.data.mappers.toDomain
import com.example.chuck_norris.network.abstractions.Either
import com.example.chuck_norris.network.exception.BaseNetworkException
import com.example.chuck_norris.network.exception.GenericNetworkException
import com.example.chuck_norris.network.manager.NetworkManager

class JokeRemoteRepositoryImpl(
    private val api: JokeApi,
    private val networkManager: NetworkManager
) : JokeRemoteRepository {

    override suspend fun getRandomJokeByCategory(category: String): Either<Joke, BaseNetworkException> =
        try {
            val data = networkManager.doAsyncRequest {
                api.getRandomJokeByCategory(category)
            }.toDomain()

            Either.value(data)
        } catch (exception: BaseNetworkException) {
            Either.error(exception)
        } catch (exception: Exception) {
            Either.error(GenericNetworkException(exception.message ?: ""))
        }

}