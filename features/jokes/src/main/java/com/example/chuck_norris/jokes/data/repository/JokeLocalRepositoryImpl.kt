package com.example.chuck_norris.jokes.data.repository

import android.content.Context
import com.example.chuck_norris.database.ChuckNorrisDatabase
import com.example.chuck_norris.domain.entities.Joke
import com.example.chuck_norris.jokes.R
import com.example.chuck_norris.jokes.data.mappers.toDomain
import com.example.chuck_norris.jokes.data.mappers.toEntity
import com.example.chuck_norris.network.abstractions.Either

class JokeLocalRepositoryImpl(
    private val context: Context,
    private val database: ChuckNorrisDatabase
) : JokeLocalRepository {

    override suspend fun save(joke: Joke): Either<Long, Exception> = try {
        val id = database.jokeDao().create(joke.toEntity())
        when {
            id > 0L -> Either.value(id)
            else -> Either.error(Exception(context.getString(R.string.could_not_favorite_joke)))
        }
    } catch (e: Exception) {
        Either.error(e)
    }

    override suspend fun findByRemoteId(id: String): Either<Joke?, Exception> = try {
        val joke = database.jokeDao().findByRemoteId(id)
        Either.value(joke?.toDomain())
    } catch (e: Exception) {
        Either.error(e)
    }

    override suspend fun findById(id: Long): Either<Joke, Exception> = try {
        val joke = database.jokeDao().findById(id)
        Either.value(joke.toDomain())
    } catch (e: Exception) {
        Either.error(e)
    }

}