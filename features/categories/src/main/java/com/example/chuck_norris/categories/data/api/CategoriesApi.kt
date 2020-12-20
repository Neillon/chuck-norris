package com.example.chuck_norris.categories.data.api

import com.example.chuck_norris.categories.data.response.JokeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoriesApi {

    /**
     * Retrieve the categories
     * [url] https://api.chucknorris.io/jokes/categories
     */
    @GET("/categories")
    fun getCategories(): Response<List<String>>

    /**
     * Retrieve a random joke by category
     * [url] https://api.chucknorris.io/jokes/random?category={category}
     */
    @GET("/random")
    fun getRandomJokeByCategory(
        @Query("category") category: String
    ): Response<JokeResponse>
}