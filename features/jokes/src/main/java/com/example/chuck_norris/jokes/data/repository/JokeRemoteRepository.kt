package com.example.chuck_norris.jokes.data.repository

import com.example.chuck_norris.common.Either
import com.example.chuck_norris.common.Repository
import com.example.chuck_norris.entities.Joke
import com.example.chuck_norris.network.exception.BaseNetworkException

interface JokeRemoteRepository : Repository<Joke> {

    /**
     * Retrieve a random joke from API
     */
    suspend fun getRandomJokeByCategory(category: String): Either<Joke, BaseNetworkException>

    /**
     * Retrieve joke by description
     */
    suspend fun searchJoke(description: String): Either<List<Joke>, BaseNetworkException>

}