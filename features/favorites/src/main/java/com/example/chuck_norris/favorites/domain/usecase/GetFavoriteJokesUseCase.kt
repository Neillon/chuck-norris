package com.example.chuck_norris.favorites.domain.usecase

import com.example.chuck_norris.common.Either
import com.example.chuck_norris.common.UseCase
import com.example.chuck_norris.entities.Joke
import com.example.chuck_norris.extensions.exhaustive
import com.example.chuck_norris.favorites.data.repository.JokesLocalRepository

class GetFavoriteJokesUseCase(
    private val repository: JokesLocalRepository
) : UseCase<Either<List<Joke>, Exception>, GetFavoriteJokesUseCase.Params> {

    class Params

    override suspend fun execute(params: Params): Either<List<Joke>, Exception> = try {
        val result = repository.getFavoriteJokes()

        when (result) {
            is Either.Value -> Either.value(result.packet)
            is Either.Error -> Either.error(result.packet)
        }.exhaustive

    } catch (e: Exception) {
        Either.error(e)
    }

}