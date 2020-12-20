package com.example.chuck_norris.network.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisApi {

    /**
     * Retrieve the categories
     * [url] https://api.chucknorris.io/jokes/categories
     */
    @GET("/categories")
    fun getCategories(): Call<List<String>>

    /**
     * Retrieve a random joke by category
     * [url] https://api.chucknorris.io/jokes/random?category={category}
     */
    @GET("/random")
    fun getRandomJokeByCategory(
        @Query("category") category: String
    )

    /**
     * Retrieve jokes by query
     */
    @GET("/search")
    fun seachJoke(
        @Query("query") query: String
    )

}