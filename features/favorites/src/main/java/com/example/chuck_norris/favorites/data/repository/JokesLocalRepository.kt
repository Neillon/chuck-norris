package com.example.chuck_norris.favorites.data.repository

import com.example.chuck_norris.abstractions.Repository
import com.example.chuck_norris.domain.entities.Joke
import com.example.chuck_norris.network.abstractions.Either

interface JokesLocalRepository: Repository<Joke> {

    suspend fun getFavoriteJokes(): Either<List<Joke>, Exception>

}