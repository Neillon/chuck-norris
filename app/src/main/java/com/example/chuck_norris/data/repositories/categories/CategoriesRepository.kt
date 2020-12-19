package com.example.chuck_norris.data.repositories.categories

import com.example.chuck_norris.data.Repository
import com.example.chuck_norris.data.api.ChuckNorrisApi
import com.example.chuck_norris.model.Category

class CategoriesRepository(
    private val chuckNorrisApi: ChuckNorrisApi
) : Repository<Category> {

    /**
     * Get all categories using the api
     */
    fun getCategories(): List<Category> {
        return chuckNorrisApi.getCategories().execute().body()!!.map { Category(it) }
    }
}