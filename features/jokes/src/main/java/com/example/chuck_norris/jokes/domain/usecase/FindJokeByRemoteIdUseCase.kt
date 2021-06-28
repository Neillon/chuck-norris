package com.example.chuck_norris.jokes.domain.usecase

import com.example.chuck_norris.common.Either
import com.example.chuck_norris.common.UseCase
import com.example.chuck_norris.entities.Joke
import com.example.chuck_norris.jokes.data.repository.JokeLocalRepository

class FindJokeByRemoteIdUseCase(
    private val repository: JokeLocalRepository
) : UseCase<Either<Joke?, Exception>, FindJokeByRemoteIdUseCase.Params> {
    data class Params(var remoteId: String)

    override suspend fun execute(params: Params): Either<Joke?, Exception> =
        repository.findByRemoteId(params.remoteId)
}