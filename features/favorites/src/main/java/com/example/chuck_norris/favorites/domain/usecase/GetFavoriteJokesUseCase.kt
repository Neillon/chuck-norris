package com.example.chuck_norris.favorites.domain.usecase

import com.example.chuck_norris.abstractions.UseCase
import com.example.chuck_norris.domain.entities.Joke
import com.example.chuck_norris.extensions.exhaustive
import com.example.chuck_norris.favorites.data.repository.JokesLocalRepository
import com.example.chuck_norris.network.abstractions.Either

class GetFavoriteJokesUseCase(
    private val repository: JokesLocalRepository
): UseCase<Either<List<Joke>, Exception>, GetFavoriteJokesUseCase.Params> {

    class Params

    override suspend fun execute(params: GetFavoriteJokesUseCase.Params): Either<List<Joke>, Exception> = try {
        val result = repository.getFavoriteJokes()

        when (result) {
            is Either.Value -> Either.value(result.packet)
            is Either.Error -> Either.error(result.packet)
        }.exhaustive

    } catch (e: Exception) {
        Either.error(e)
    }

}