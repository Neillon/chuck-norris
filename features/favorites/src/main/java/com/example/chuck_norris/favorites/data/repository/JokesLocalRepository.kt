package com.example.chuck_norris.favorites.data.repository

import com.example.chuck_norris.common.Either
import com.example.chuck_norris.common.Repository
import com.example.chuck_norris.entities.Joke

interface JokesLocalRepository : Repository<Joke> {

    suspend fun getFavoriteJokes(): Either<List<Joke>, Exception>

}