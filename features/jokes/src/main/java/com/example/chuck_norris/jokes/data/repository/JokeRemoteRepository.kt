package com.example.chuck_norris.jokes.data.repository

import com.example.chuck_norris.abstractions.Repository
import com.example.chuck_norris.domain.entities.Joke
import com.example.chuck_norris.network.abstractions.Either
import com.example.chuck_norris.network.exception.BaseNetworkException

interface JokeRemoteRepository: Repository<Joke> {

    /**
     * Retrieve a random joke from API
     */
    suspend fun getRandomJokeByCategory(category: String): Either<Joke, BaseNetworkException>

}