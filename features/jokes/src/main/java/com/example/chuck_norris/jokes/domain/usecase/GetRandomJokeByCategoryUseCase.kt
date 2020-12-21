package com.example.chuck_norris.jokes.domain.usecase

import com.example.chuck_norris.abstractions.UseCase
import com.example.chuck_norris.domain.entities.Joke
import com.example.chuck_norris.jokes.data.repository.JokeRemoteRepository
import com.example.chuck_norris.network.abstractions.Either

class GetRandomJokeByCategoryUseCase(
    private val repository: JokeRemoteRepository
) : UseCase<Either<Joke, Exception>, GetRandomJokeByCategoryUseCase.Params> {

    data class Params(var category: String)

    override suspend fun execute(params: Params): Either<Joke, Exception> {
        return repository.getRandomJokeByCategory(params.category)
    }
}