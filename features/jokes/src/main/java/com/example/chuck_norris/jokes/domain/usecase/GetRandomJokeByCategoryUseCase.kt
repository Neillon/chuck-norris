package com.example.chuck_norris.jokes.domain.usecase

import com.example.chuck_norris.common.Either
import com.example.chuck_norris.common.UseCase
import com.example.chuck_norris.entities.Joke
import com.example.chuck_norris.jokes.data.repository.JokeRemoteRepository

class GetRandomJokeByCategoryUseCase(
    private val repository: JokeRemoteRepository
) : UseCase<Either<Joke, Exception>, GetRandomJokeByCategoryUseCase.Params> {

    data class Params(var category: String)

    override suspend fun execute(params: Params): Either<Joke, Exception> {
        return repository.getRandomJokeByCategory(params.category)
    }
}