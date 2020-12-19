package com.example.chuck_norris.data.repositories.jokes

import com.example.chuck_norris.data.api.ChuckNorrisApi
import com.example.chuck_norris.model.Category
import com.example.chuck_norris.model.Joke

class JokesRepository(
    private val chuckNorrisApi: ChuckNorrisApi
) {

    /**
     * Get a random joke given a category
     */
    fun getRandomJokeByCategory(category: String): Joke {
        return Joke(
            "",
            "",
            "",
            "",
            listOf(Category(category))
        )
    }

}