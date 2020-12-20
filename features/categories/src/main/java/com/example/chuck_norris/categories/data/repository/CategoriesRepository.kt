package com.example.chuck_norris.categories.data.repository

import com.example.chuck_norris.categories.domain.Category
import com.example.chuck_norris.network.abstractions.Either
import com.example.chuck_norris.abstractions.Repository
import com.example.chuck_norris.network.error_handling.NetworkError

interface CategoriesRepository : Repository<Category> {
    suspend fun getCategories(): Either<List<Category>, NetworkError>
}