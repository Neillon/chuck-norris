package com.example.chuck_norris.jokes.data.usecase

import com.example.chuck_norris.abstractions.UseCase
import com.example.chuck_norris.domain.entities.Joke
import com.example.chuck_norris.jokes.data.repository.JokeLocalRepository
import com.example.chuck_norris.network.abstractions.Either

class FindJokeByRemoteIdUseCase(
    private val repository: JokeLocalRepository
) : UseCase<Either<Joke?, Exception>, FindJokeByRemoteIdUseCase.Params> {
    data class Params(var remoteId: String)

    override suspend fun execute(params: Params): Either<Joke?, Exception> =
        repository.findByRemoteId(params.remoteId)
}