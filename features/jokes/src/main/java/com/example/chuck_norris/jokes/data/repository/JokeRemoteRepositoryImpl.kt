package com.example.chuck_norris.jokes.data.repository

import com.example.chuck_norris.common.Either
import com.example.chuck_norris.entities.Joke
import com.example.chuck_norris.jokes.data.api.JokeApi
import com.example.chuck_norris.jokes.data.mappers.toDomain
import com.example.chuck_norris.jokes.data.mappers.toJokeList
import com.example.chuck_norris.network.exception.BaseNetworkException
import com.example.chuck_norris.network.exception.GenericNetworkException
import com.example.chuck_norris.network.manager.NetworkManager

class JokeRemoteRepositoryImpl(
    private val api: JokeApi
) : JokeRemoteRepository {

    override suspend fun getRandomJokeByCategory(category: String): Either<Joke, BaseNetworkException> =
        try {
            val data = NetworkManager.doAsyncRequest {
                api.getRandomJokeByCategory(category)
            }.toDomain()

            Either.value(data)
        } catch (exception: BaseNetworkException) {
            Either.error(exception)
        } catch (exception: Exception) {
            Either.error(
                GenericNetworkException(
                    exception.message ?: ""
                )
            )
        }

    override suspend fun searchJoke(description: String): Either<List<Joke>, BaseNetworkException> =
        try {
            val data = NetworkManager.doAsyncRequest {
                api.search(description)
            }
            Either.value(data.result.toJokeList())
        } catch (e: BaseNetworkException) {
            Either.error(e)
        } catch (e: Exception) {
            Either.error(GenericNetworkException(e.message ?: ""))
        }

}