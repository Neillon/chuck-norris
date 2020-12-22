package com.example.chuck_norris.jokes.domain.usecase

import com.example.chuck_norris.abstractions.UseCase
import com.example.chuck_norris.domain.entities.Joke
import com.example.chuck_norris.extensions.exhaustive
import com.example.chuck_norris.jokes.data.repository.JokeRemoteRepository
import com.example.chuck_norris.network.abstractions.Either

class SearchJokeByDescriptionUseCase(
    private val repository: JokeRemoteRepository
):
    UseCase<Either<List<Joke>, String>, SearchJokeByDescriptionUseCase.Params> {

    data class Params(var description: String)

    override suspend fun execute(params: Params): Either<List<Joke>, String> {
        val result = repository.searchJoke(params.description)

        return when (result) {
            is Either.Value -> Either.value(result.packet)
            is Either.Error -> Either.error(result.packet.errorMessage)
        }.exhaustive
    }
}