package com.example.chuck_norris.network.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisApi {

    /**
     * Retrieve jokes by query
     */
    @GET("/search")
    fun seachJoke(
        @Query("query") query: String
    )

}