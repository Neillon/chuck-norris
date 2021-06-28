package com.example.chuck_norris.jokes.data.repository

import com.example.chuck_norris.common.Repository
import com.example.chuck_norris.entities.Joke

interface JokeLocalRepository : Repository<Joke> {
    suspend fun save(joke: Joke): com.example.chuck_norris.common.Either<Long, Exception>
    suspend fun findByRemoteId(id: String): com.example.chuck_norris.common.Either<Joke?, Exception>
    suspend fun findById(id: Long): com.example.chuck_norris.common.Either<Joke, Exception>

}