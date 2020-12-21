package com.example.chuck_norris.jokes.data.repository

import com.example.chuck_norris.abstractions.Repository
import com.example.chuck_norris.domain.entities.Joke
import com.example.chuck_norris.network.abstractions.Either

interface JokeLocalRepository : Repository<Joke> {
    suspend fun save(joke: Joke): Either<Long, Exception>
    suspend fun findByRemoteId(id: String): Either<Joke?, Exception>
    suspend fun findById(id: Long): Either<Joke, Exception>

}