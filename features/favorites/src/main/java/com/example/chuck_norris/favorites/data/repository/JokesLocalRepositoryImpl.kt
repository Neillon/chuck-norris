package com.example.chuck_norris.favorites.data.repository

import com.example.chuck_norris.database.ChuckNorrisDatabase
import com.example.chuck_norris.domain.entities.Joke
import com.example.chuck_norris.favorites.data.mappers.toDomain
import com.example.chuck_norris.network.abstractions.Either

class JokesLocalRepositoryImpl(
    private val database: ChuckNorrisDatabase
): JokesLocalRepository {

    override suspend fun getFavoriteJokes(): Either<List<Joke>, Exception> = try {
            Either.value(database.jokeDao().getFavoriteJokes().toDomain())
        } catch (e: Exception) {
            Either.error(e)
        }

}