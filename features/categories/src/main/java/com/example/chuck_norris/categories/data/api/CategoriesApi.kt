package com.example.chuck_norris.categories.data.api

import retrofit2.Response
import retrofit2.http.GET

interface CategoriesApi {

    /**
     * Retrieve the categories
     * [url] https://api.chucknorris.io/jokes/categories
     */
    @GET("/jokes/categories")
    suspend fun getCategories(): Response<List<String>>

}