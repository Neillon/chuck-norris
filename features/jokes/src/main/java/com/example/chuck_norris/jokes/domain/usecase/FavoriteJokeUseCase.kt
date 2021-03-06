package com.example.chuck_norris.jokes.domain.usecase

import com.example.chuck_norris.common.Either
import com.example.chuck_norris.common.UseCase
import com.example.chuck_norris.entities.Joke
import com.example.chuck_norris.extensions.exhaustive
import com.example.chuck_norris.jokes.data.repository.JokeLocalRepository

class FavoriteJokeUseCase(
    private val repository: JokeLocalRepository,
    private val findJokeByRemoteIdUseCase: FindJokeByRemoteIdUseCase
) : UseCase<Either<Joke, Exception>, FavoriteJokeUseCase.Params> {

    data class Params(var joke: Joke)

    override suspend fun execute(params: Params): Either<Joke, Exception> {
        val result =
            findJokeByRemoteIdUseCase.execute(FindJokeByRemoteIdUseCase.Params(params.joke.id))

        return when (result) {
            is Either.Value -> {
                return if (result.packet != null)
                    Either.value(result.packet!!)
                else
                    callFavotiteJoke(params)
            }

            is Either.Error -> Either.Error(result.packet)
        }.exhaustive
    }

    private suspend fun callFavotiteJoke(params: Params): Either<Joke, Exception> {
        val result = repository.save(params.joke)
        return when (result) {
            is Either.Value -> repository.findById(result.packet)
            is Either.Error -> Either.error(result.packet)
        }.exhaustive
    }
}