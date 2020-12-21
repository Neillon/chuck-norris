package com.example.chuck_norris.jokes.data.api

import com.example.chuck_norris.jokes.data.response.JokeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JokeApi {

    /**
     * Retrieve a random joke by category
     * [url] https://api.chucknorris.io/jokes/random?category={category}
     */
    @GET("/jokes/random")
    suspend fun getRandomJokeByCategory(
        @Query("category") category: String
    ): Response<JokeResponse>

}